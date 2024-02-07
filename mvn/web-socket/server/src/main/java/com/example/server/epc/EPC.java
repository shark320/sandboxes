package com.example.server.epc;

public class EPC {
    String bin_epc;
    String company_prefix;
    String item_reference;
    String serial_number;

    public EPC(String bin_epc, String company_prefix, String item_reference, String serial_number){
        this.bin_epc = bin_epc;
        this.company_prefix = company_prefix;
        this.item_reference = item_reference;
        this.serial_number = serial_number;
    }

    public String getBin_epc() {
        return bin_epc;
    }

    public void setBin_epc(String bin_epc) {
        this.bin_epc = bin_epc;
    }

    public String getCompany_prefix() {
        return company_prefix;
    }

    public void setCompany_prefix(String company_prefix) {
        this.company_prefix = company_prefix;
    }

    public String getItem_reference() {
        return item_reference;
    }

    public void setItem_reference(String item_reference) {
        this.item_reference = item_reference;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    @Override
    public String toString() {
        return "EPC{" +
                "bin_epc='" + bin_epc + '\'' +
                ", company_prefix='" + company_prefix + '\'' +
                ", item_reference='" + item_reference + '\'' +
                ", serial_number='" + serial_number + '\'' +
                '}';
    }
}
