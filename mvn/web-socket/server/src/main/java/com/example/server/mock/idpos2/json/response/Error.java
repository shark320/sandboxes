package com.example.server.mock.idpos2.json.response;

public class Error {
    private int scan_id;

    private int code;

    private String description;

    public Error(int scan_id, int code, String description) {
        this.scan_id = scan_id;
        this.code = code;
        this.description = description;
    }

    public Error() {
    }

    public int getScan_id() {
        return scan_id;
    }

    public void setScan_id(int scan_id) {
        this.scan_id = scan_id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Error{" +
                "scan_id=" + scan_id +
                ", code=" + code +
                ", description='" + description + '\'' +
                '}';
    }
}
