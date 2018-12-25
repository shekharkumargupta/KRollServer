package com.kroll.websocket.handler;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MessageHandler extends TextWebSocketHandler{

    public void handleTextMessage(WebSocketSession session, String textMessage){
        System.out.println("Session: "+session.getId());
        System.out.println("Message: "+textMessage);
    }
}
