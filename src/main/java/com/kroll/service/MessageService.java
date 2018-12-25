package com.kroll.service;

import java.io.IOException;

import javax.websocket.Session;

public interface MessageService {

    public void handleOpen(String loginId, Session userSession) throws IOException;

    public void handleMessage(String messageString, Session userSession) throws IOException;

    public void handleClose(Session userSession);
}
