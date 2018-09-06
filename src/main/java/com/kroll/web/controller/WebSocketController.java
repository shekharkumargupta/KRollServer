package com.kroll.web.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebSocketController {

    @RequestMapping(value = "/")
    public String test(){
        return "Test success!";
    }
    
    
    @MessageMapping("/ping")
    @SendTo("/topic/greetings")
    public String doMessage(String message){
        return "Hello "+message;
    }
}
