package org.example;
import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.*;

public class WebSocketConnector {
    private final String uriString;

    private WebSocketClient client;
    private ExecutorService executor;

    public WebSocketConnector(String uriString) {
        this.uriString = uriString;
        client = new WebSocketClient();
        executor = Executors.newFixedThreadPool(2);
    }

    public void connect() throws Exception {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        URI uri = new URI(this.uriString);
        System.out.println("Connecting to the: " + uri);
        Session session = container.connectToServer(client, uri);
        System.out.println(session.isOpen());
        // Separate threads for request and response
        executor.submit(this::processRequests);
        executor.submit(this::processResponses);
    }

    private void processRequests() {
        // Process requests here
        System.out.println("Processing a request");
        try {
            client.sendMessage("{'name':'eee'}");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void processResponses() {
        // Responses are processed in the WebSocketClient onMessage method
    }
}
