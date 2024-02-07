package com.example.server.mock.idpos2.json.request;

public class Request {

    private Identify identify;

    private StartScan start_scan;

    private StopScan stop_scan;

    public Identify getIdentify() {
        return identify;
    }

    public void setIdentify(Identify identify) {
        this.identify = identify;
    }

    public StartScan getStart_scan() {
        return start_scan;
    }

    public void setStart_scan(StartScan start_scan) {
        this.start_scan = start_scan;
    }

    public StopScan getStop_scan() {
        return stop_scan;
    }

    public void setStop_scan(StopScan stop_scan) {
        this.stop_scan = stop_scan;
    }

    @Override
    public String toString() {
        return "Request{" +
                "identify=" + identify +
                ", start_scan=" + start_scan +
                ", stop_scan=" + stop_scan +
                '}';
    }
}
