package com.example.server.test.controllers;

import com.example.server.test.messages.Greeting;
import com.example.server.test.messages.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {


    @MessageMapping("/")
    @SendTo("/")
    public Greeting greeting(HelloMessage message) throws Exception {
        System.out.println("Message:" + message);
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }



}