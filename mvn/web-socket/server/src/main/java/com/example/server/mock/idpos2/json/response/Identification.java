package com.example.server.mock.idpos2.json.response;

import com.example.server.mock.idpos2.device.State;

public class Identification {

    private String device_name;

    private String mac_address;

    private String system_id;

    private State state;

    public Identification() {
    }

    public Identification(String device_name, String mac_address, String system_id, State state) {
        this.device_name = device_name;
        this.mac_address = mac_address;
        this.system_id = system_id;
        this.state = state;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public String getSystem_id() {
        return system_id;
    }

    public void setSystem_id(String system_id) {
        this.system_id = system_id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Identification{" +
                "device_name='" + device_name + '\'' +
                ", mac_address='" + mac_address + '\'' +
                ", system_id='" + system_id + '\'' +
                ", state=" + state +
                '}';
    }
}
