package org.example.okhttp3;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

import java.util.concurrent.TimeUnit;

public class CustomWebSocketClient {

    private WebSocket webSocket;

    public CustomWebSocketClient(String urlString){

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url(urlString)
                .build();

        webSocket = client.newWebSocket(request, new CustomWebSocketListener());
    }

    public void sendMessage(String message) {
        // Send a message to the server
        webSocket.send(message);
    }

    public void closeConnection() {
        // Close the WebSocket connection with a normal closure code and no reason
        webSocket.close(1000, null);
    }
}
