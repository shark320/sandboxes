package com.example.server;

import com.example.server.epc.EPC;
import com.example.server.epc.EpcDecoder;
import com.example.server.epc.EpcEncoder;
import com.example.server.mock.idpos2.device.Device;
import com.example.server.mock.idpos2.device.State;
import com.example.server.mock.idpos2.json.request.Identify;
import com.example.server.mock.idpos2.json.request.Request;
import com.example.server.mock.idpos2.json.response.Identification;
import com.example.server.mock.idpos2.json.response.Response;
import com.example.server.mock.idpos2.json.response.ScannedTag;
import com.gk_software.pos.api.model.types.CheckDigitRule;
import com.gk_software.pos.api.pos_hal.input.processing.common.config.ChecksumValidator;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

import java.util.zip.DeflaterInputStream;

import static com.example.server.epc.EpcDecoder.ITEM_REFERENCE_LENGTH;

public class Main {

    private static final int COMPANY_PREFIX_LENGTH = 8;

    private static final int ITEM_REFERENCE_LENGTH = 5;

    private static final int EAN_LENGTH = 13;

    protected static String getGtinNullSafe(String spcHex) {
        //  String decoded = item.getDecoded();
        String gtin = "";

        EPC epcObject = EpcDecoder.decodeEpc(spcHex);
        if (epcObject != null
                && epcObject.getCompany_prefix() != null
                && epcObject.getItem_reference() != null
                && epcObject.getItem_reference().length() >= 5
        ) {
            gtin = getEanWithChecksumDigit(epcObject);
        }


        return gtin;
    }

    public static String getEanWithChecksumDigit(EPC epcObject) {
        String finalbarcode = ChecksumValidator.calculate(
                StringUtils.leftPad(epcObject.getCompany_prefix(), COMPANY_PREFIX_LENGTH, "0") +
                        epcObject.getItem_reference().substring(epcObject.getItem_reference().length() - ITEM_REFERENCE_LENGTH, epcObject.getItem_reference().length())
                , CheckDigitRule.GC_1
        );
        System.out.println(epcObject.getItem_reference().substring(epcObject.getItem_reference().length() - ITEM_REFERENCE_LENGTH, epcObject.getItem_reference().length()));
        return finalbarcode.substring(finalbarcode.length() - EAN_LENGTH, finalbarcode.length());
    }

    public static void main(String[] args) {
        String testEpcHex = "30347A12301D8EF84BC77670";
        System.out.println(testEpcHex);
        EPC testEpc = EpcDecoder.decodeEpc(testEpcHex);
        System.out.println(testEpc);
        String bin_epc_default = StringUtils.leftPad("", 14, "1");
        String company_prefix_default = "0";
        String item_ref_default = "111";
        String serial_number_default = "241789531760";
        EPC epc = new EPC(bin_epc_default, company_prefix_default, item_ref_default, serial_number_default);
        System.out.println(epc);
        System.out.println(EpcEncoder.encodeEpc(epc));

        System.out.println(getGtinNullSafe(EpcEncoder.encodeEpc(epc)));
    }
}
