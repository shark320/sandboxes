package com.example.server.mock.idpos2.json.response;

public class ScanStarted {

    private int scan_id;

    private String timestamp;

    public ScanStarted(int scan_id, String timestamp) {
        this.scan_id = scan_id;
        this.timestamp = timestamp;
    }

    public int getScan_id() {
        return scan_id;
    }

    public void setScan_id(int scan_id) {
        this.scan_id = scan_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
