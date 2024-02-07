package com.example.server.config;

import com.example.server.mock.idpos2.controllers.IDPOS2WebSocketHandler;
import com.example.server.test.controllers.TestWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new TestWebSocketHandler(), "/test");
        registry.addHandler(new IDPOS2WebSocketHandler(), "/idpos2");
    }
}