package com.example.server.mock.idpos2.controllers;

import com.example.server.mock.idpos2.device.Device;
import com.example.server.mock.idpos2.device.RfidTag;
import com.example.server.mock.idpos2.device.State;
import com.example.server.mock.idpos2.json.request.Request;
import com.example.server.mock.idpos2.json.response.*;
import com.example.server.mock.idpos2.json.response.Error;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class IDPOS2WebSocketHandler extends TextWebSocketHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(IDPOS2WebSocketHandler.class);

    private Device device = new Device();

    private TagsThread tagsThread;

    private Gson gson = new Gson();

    private final Semaphore tagsScanSemaphore = new Semaphore(1);


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        LOGGER.info("Received message: " + message.getPayload());
        Request request = gson.fromJson(message.getPayload(), Request.class);
        LOGGER.info("Processing request: " + request);
        if (request.getIdentify() != null) {
            processIdentify(session);
            return;
        }
        if (request.getStart_scan() != null) {
            processStartScan(session);
            return;
        }
        if (request.getStop_scan() != null) {
            processStopScan(session, request);
            return;
        }
        processError(session, "Unable to identify request type.");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        LOGGER.info("Connection closed: " + session.getAttributes());
        this.device.reset();
        super.afterConnectionClosed(session, status);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        LOGGER.info("Connection established: " + session.getAttributes());
        super.afterConnectionEstablished(session);
    }

    private void processIdentify(WebSocketSession session) throws IOException {
        if (this.device.getCurrentState() == State.unknown) {
            device.moveToState(State.idle);
        }
        Response response = new Response();
        Identification identification = new Identification();
        identification.setState(device.getCurrentState());
        identification.setDevice_name(device.getName());
        identification.setMac_address(device.getMacAddress());
        identification.setSystem_id(device.getSystemId());
        response.setIdentification(identification);
        LOGGER.info("Sending identification response: " + response);
        session.sendMessage(new TextMessage(gson.toJson(response)));
    }

    private void processStartScan(WebSocketSession session) throws IOException, InterruptedException {
        if (!device.moveToState(State.scanning)) {
            processError(session, "Unable to start scanning from the current state: [" + device.getCurrentState() + "]");
            return;
        }
        if (tagsThread != null && tagsThread.continuous) {
            processError(session, "Tags scanning is running");
            return;
        }
        if (tagsScanSemaphore.tryAcquire()) {
            Response response = new Response();
            ScanStarted scanStarted = new ScanStarted();
            scanStarted.setScan_id(device.startScan());
            scanStarted.setTimestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
            response.setScan_started(scanStarted);
            LOGGER.info("Sending scan started response: " + response);
            session.sendMessage(new TextMessage(gson.toJson(response)));
            tagsThread = new TagsThread();
            tagsThread.setSession(session);
            tagsThread.startScan();
        }

    }

    private void processStopScan(WebSocketSession session, Request request) throws IOException {
        if (!device.moveToState(State.idle)) {
            processError(session, "Unable to stop scanning in the current state: [" + device.getCurrentState() + "]");
            return;
        }
        int scanId = request.getStop_scan().getScan_id();
        if (!this.device.matchScanId(scanId)) {
            processError(session, "Unknown scan id: " + scanId);
            return;
        }

        tagsThread.stopScan(scanSession -> {
            Response response = new Response();

            int scanCount = device.stopScan(scanId);
            StoppedScan stoppedScan = new StoppedScan();
            stoppedScan.setScan_count(scanCount);
            stoppedScan.setScan_id(scanId);
            stoppedScan.setTimestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
            response.setStopped_scan(stoppedScan);
            LOGGER.info("Sending stop scan response: " + response);
            session.sendMessage(new TextMessage(gson.toJson(response)));

        });

        tagsScanSemaphore.release();

    }


    private void processError(WebSocketSession session, String message) throws IOException {
        Response response = new Response();
        Error error = new Error();
        response.setError(error);
        error.setDescription(message);
        LOGGER.error("");
        session.sendMessage(new TextMessage(gson.toJson(response)));
    }

    private void processScanTag(WebSocketSession session) throws IOException {
        if (device.getCurrentState() != State.scanning) {
            return;
        }
        for (RfidTag tag: device.scanAllTags()){
            LOGGER.info(tag.toString());
            Response response = new Response();
            ScannedTag scannedTag = new ScannedTag();
            scannedTag.setEpc_hex(tag.getEpc());
            scannedTag.setGs1_elementstring(tag.getGs1ElementString());
            scannedTag.setPure_identity_uri(tag.getPureIdentityUri());
            scannedTag.setTimestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
            response.setScanned_tag(scannedTag);
            LOGGER.info("Sending scanned tag response: " + response);
            session.sendMessage(new TextMessage(gson.toJson(response)));
        }

    }

    private class TagsThread extends Thread {

        private static final String THREAD_NAME = "TAGS_SCANNING_THREAD";

        private WebSocketSession session;

        private volatile boolean continuous = true;

        private ScanStopCallback stopCallback;

        public TagsThread(WebSocketSession session) {
            super(THREAD_NAME);
            setSession(session);
        }

        public TagsThread() {
            super(THREAD_NAME);
        }

        public void setSession(WebSocketSession session) {
            this.session = session;
        }

        public void stopScan(ScanStopCallback stopCallback) {
            this.stopCallback = stopCallback;
            this.continuous = false;
        }

        public void startScan() {
            this.continuous = true;
            this.start();
        }

        @Override
        public void run() {
            if (this.session == null) {
                LOGGER.error("Session is null!");
            }
            try {

                processScanTag(session);
                tagsScanSemaphore.acquire();
                stopCallback.sendScanStopped(session);
                tagsScanSemaphore.release();
            } catch (IOException e) {
                LOGGER.error("", e);
            } catch (InterruptedException e) {
                LOGGER.error("", e);
            }
        }
    }

    @FunctionalInterface
    private interface ScanStopCallback {
        void sendScanStopped(WebSocketSession session) throws IOException;
    }
}
