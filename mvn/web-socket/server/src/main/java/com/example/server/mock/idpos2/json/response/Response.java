package com.example.server.mock.idpos2.json.response;

public class Response {

    private Identification identification;

    private ScanStarted scan_started;

    private ScannedTag scanned_tag;

    private StoppedScan stopped_scan;
    private Error error;

    public Identification getIdentification() {
        return identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
    }

    public ScanStarted getScan_started() {
        return scan_started;
    }

    public void setScan_started(ScanStarted scan_started) {
        this.scan_started = scan_started;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public ScannedTag getScanned_tag() {
        return scanned_tag;
    }

    public void setScanned_tag(ScannedTag scanned_tag) {
        this.scanned_tag = scanned_tag;
    }

    public StoppedScan getStopped_scan() {
        return stopped_scan;
    }

    public void setStopped_scan(StoppedScan stopped_scan) {
        this.stopped_scan = stopped_scan;
    }

    @Override
    public String toString() {
        return "Response{" +
                "identification=" + identification +
                ", scan_started=" + scan_started +
                ", scanned_tag=" + scanned_tag +
                ", stopped_scan=" + stopped_scan +
                ", error=" + error +
                '}';
    }
}
