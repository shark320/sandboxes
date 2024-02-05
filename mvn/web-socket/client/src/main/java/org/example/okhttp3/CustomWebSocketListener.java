package org.example.okhttp3;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.jetbrains.annotations.NotNull;

public class CustomWebSocketListener extends WebSocketListener {

    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        System.out.println("Connection opened.");
        //super.onOpen(webSocket, response);
    }

    @Override
    public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        System.out.println("Connection closed.");
        //super.onClosed(webSocket, code, reason);
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        System.out.println(Thread.currentThread().getName());
        System.out.println("Received message: " + text);
        //super.onMessage(webSocket, text);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        // Print an error message when the connection fails
        System.out.println("Error: " + t.getMessage());
    }
}
