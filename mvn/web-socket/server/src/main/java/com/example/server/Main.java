package com.example.server;

import com.example.server.mock.idpos2.device.State;
import com.example.server.mock.idpos2.json.request.Identify;
import com.example.server.mock.idpos2.json.request.Request;
import com.example.server.mock.idpos2.json.response.Identification;
import com.example.server.mock.idpos2.json.response.Response;
import com.google.gson.Gson;

public class Main {

    private static final String IDENTIFICATION_JSON = "{\n" +
            "\"identification\": {\n" +
            "\"device_name\": \"Desk 3\",\n" +
            "\"mac_address\": \"00:0D:A0:11:12:34\",\n" +
            "\"system_id\": \"7e1d6794-7e8d-44c2-9d83-90558f54bb4d\",\n" +
            "\"state\": \"scanning\"\n" +
            "}\n" +
            "}";

    public static void main(String[] args) {
        Gson gson = new Gson();
        Response response = new Response();
        response.setIdentification(new Identification("ispos2", "00:0D:A0:11:12:34", "7e1d6794-7e8d-44c2-9d83-90558f54bb4d", State.idle));
        System.out.println(gson.toJson(response));

        Response responseTest = gson.fromJson(IDENTIFICATION_JSON, Response.class);
        System.out.println(responseTest);
    }
}
