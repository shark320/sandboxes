package com.example.server.epc;

import com.example.server.mock.idpos2.controllers.IDPOS2WebSocketHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.Locale;

public class EpcEncoder {

    private static final Logger LOGGER = LoggerFactory.getLogger(EpcEncoder.class);

    public static String encodeEpc(EPC epc) {
        try {
            String bin_epc = epc.getBin_epc();
            String company_prefix = StringUtils.leftPad(new BigInteger(epc.getCompany_prefix()).toString(2), 24, "0");
            String item_reference = StringUtils.leftPad(new BigInteger(epc.getItem_reference()).toString(2), 20, "0");
            String serial_number = StringUtils.leftPad(new BigInteger(epc.getSerial_number()).toString(2), 38, "0");

            String bin_epc_full = bin_epc.substring(0, 14) + company_prefix + item_reference + serial_number;
            return binToHex(bin_epc_full);
        } catch(Exception e) {
            LOGGER.error("Invalid EPC!" + epc);
            return null;
        }
    }

     static String binToHex(String bin) {
        return new BigInteger(bin, 2).toString(16).toUpperCase(Locale.ENGLISH);
    }
}
