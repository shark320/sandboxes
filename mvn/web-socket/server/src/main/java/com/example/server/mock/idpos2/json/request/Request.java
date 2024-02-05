package com.example.server.mock.idpos2.json.request;

public class Request {

    private Identify identify;

    private StartScan start_scan;

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


}
