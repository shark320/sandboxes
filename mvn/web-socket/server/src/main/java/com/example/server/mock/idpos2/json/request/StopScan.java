package com.example.server.mock.idpos2.json.request;

public class StopScan {

    private int scan_id;

    public StopScan(int scan_id) {
        this.scan_id = scan_id;
    }

    public StopScan() {
    }

    public int getScan_id() {
        return scan_id;
    }

    public void setScan_id(int scan_id) {
        this.scan_id = scan_id;
    }
}
