package org.example;

public class Main {
    public static void main(String[] args) throws Exception {
        WebSocketConnector connector = new WebSocketConnector("ws://localhost:8080/test");
        connector.connect();
    }
}