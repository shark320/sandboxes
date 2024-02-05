package org.example;

import org.example.okhttp3.CustomWebSocketClient;

public class Main {
    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName());
        // Create a WebSocket client with the server endpoint URL
        CustomWebSocketClient client = new CustomWebSocketClient("ws://localhost:8080/test");

        // Send a hello message to the server
        client.sendMessage("Hello, server!");

        // Close the connection after 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        client.closeConnection();
    }
}