package com.example.server.mock.idpos2.json.response;

public class ScannedTag {

    private int scan_id;

    private String timestamp;

    private String epc_hex;

    private String pure_identity_uri;

    private String gs1_elementstring;

    public ScannedTag(int scan_id, String timestamp, String epc_hex, String pure_identity_uri, String gs1_elementstring) {
        this.scan_id = scan_id;
        this.timestamp = timestamp;
        this.epc_hex = epc_hex;
        this.pure_identity_uri = pure_identity_uri;
        this.gs1_elementstring = gs1_elementstring;
    }

    public ScannedTag() {
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

    public String getEpc_hex() {
        return epc_hex;
    }

    public void setEpc_hex(String epc_hex) {
        this.epc_hex = epc_hex;
    }

    public String getPure_identity_uri() {
        return pure_identity_uri;
    }

    public void setPure_identity_uri(String pure_identity_uri) {
        this.pure_identity_uri = pure_identity_uri;
    }

    public String getGs1_elementstring() {
        return gs1_elementstring;
    }

    public void setGs1_elementstring(String gs1_elementstring) {
        this.gs1_elementstring = gs1_elementstring;
    }

    @Override
    public String toString() {
        return "ScannedTag{" +
                "scan_id=" + scan_id +
                ", timestamp='" + timestamp + '\'' +
                ", epc_hex='" + epc_hex + '\'' +
                ", pure_identity_uri='" + pure_identity_uri + '\'' +
                ", gs1_elementstring='" + gs1_elementstring + '\'' +
                '}';
    }
}
