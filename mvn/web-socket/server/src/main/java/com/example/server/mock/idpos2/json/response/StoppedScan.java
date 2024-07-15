package com.example.server.mock.idpos2.json.response;

public class StoppedScan {

    private int scan_id;

    private String timestamp;

    private int scan_count;

    public StoppedScan(int scan_id, String timestamp, int scan_count) {
        this.scan_id = scan_id;
        this.timestamp = timestamp;
        this.scan_count = scan_count;
    }

    public StoppedScan() {
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

    public int getScan_count() {
        return scan_count;
    }

    public void setScan_count(int scan_count) {
        this.scan_count = scan_count;
    }

    @Override
    public String toString() {
        return "StoppedScan{" +
                "scan_id=" + scan_id +
                ", timestamp='" + timestamp + '\'' +
                ", scan_count=" + scan_count +
                '}';
    }
}
