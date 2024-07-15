package com.example.server.mock.idpos2.device;

public class RfidTag {

    private final String epc;

    private final String pureIdentityUri;

    private final String gs1ElementString;

    public RfidTag(String epc, String pureIdentityUri, String gs1ElementString) {
        this.epc = epc;
        this.pureIdentityUri = pureIdentityUri;
        this.gs1ElementString = gs1ElementString;
    }

    public String getEpc() {
        return epc;
    }

    public String getPureIdentityUri() {
        return pureIdentityUri;
    }

    public String getGs1ElementString() {
        return gs1ElementString;
    }

    @Override
    public String toString() {
        return "RfidTag{" +
                "epc='" + epc + '\'' +
                ", pureIdentityUri='" + pureIdentityUri + '\'' +
                ", gs1ElementString='" + gs1ElementString + '\'' +
                '}';
    }
}
