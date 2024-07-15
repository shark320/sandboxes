package com.example.server.epc;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.Locale;

public class EpcDecoder {

    private static final Logger LOGGER = LoggerFactory.getLogger(EpcDecoder.class);
    public static final int ITEM_REFERENCE_LENGTH = 6;
    public static final int COMPANY_PREFIX_LENGTH = 7;
    private static final String ZERO = "0";
    private static final String URL_PREFIX = "urn:epc:id:sgtin:";

    public static String decode(String epc){
        try {
            EPC dEcp =  decodeEpc(epc);
            return URL_PREFIX + dEcp.getCompany_prefix() + "." + dEcp.getItem_reference() + "." + dEcp.getSerial_number();
        } catch(Exception e) {
            LOGGER.error("Invalid EPC... EPC = "+ epc);
            return null;
        }
    }

    public static EPC decodeEpc(String epc){

        try {
            String bin_epc = hexToBin2(epc.toUpperCase(Locale.ENGLISH));
            String company_prefix = new BigInteger(bin_epc.substring(14,38), 2).toString();
            String item_reference = new BigInteger(bin_epc.substring(38,58), 2).toString();
            String serial_number = new BigInteger(bin_epc.substring(58,96), 2).toString();

            item_reference =  StringUtils.leftPad(item_reference,ITEM_REFERENCE_LENGTH,ZERO);
            company_prefix = StringUtils.leftPad(company_prefix,COMPANY_PREFIX_LENGTH,ZERO);
            serial_number = serial_number.replaceAll("^0+", "");
            return  new EPC(bin_epc,company_prefix, item_reference, serial_number);

        } catch(Exception e) {
            LOGGER.error("Invalid EPC... EPC = "+ epc);
            return null;
        }
    }

    private static String hexToBin2(String hex){
        return hex.replaceAll("0", "0000")
                .replaceAll("1", "0001")
                .replaceAll("2", "0010")
                .replaceAll("3", "0011")
                .replaceAll("4", "0100")
                .replaceAll("5", "0101")
                .replaceAll("6", "0110")
                .replaceAll("7", "0111")
                .replaceAll("8", "1000")
                .replaceAll("9", "1001")
                .replaceAll("A", "1010")
                .replaceAll("B", "1011")
                .replaceAll("C", "1100")
                .replaceAll("D", "1101")
                .replaceAll("E", "1110")
                .replaceAll("F", "1111");
    }
}


