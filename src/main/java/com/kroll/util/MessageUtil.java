package com.kroll.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.Session;

import com.google.gson.Gson;
import com.kroll.domain.CustomerOrder;
import com.kroll.domain.Envelope;
import com.kroll.domain.Login;

public class MessageUtil {

    private static MessageUtil instance = null;
    private Map<String, Session> onlineUsersMap = Collections.synchronizedMap(new HashMap<String, Session>());
    private Map<Long, List<CustomerOrder>> orderMap = new HashMap<>();

    public synchronized void join(String loginId, Session session) {
        if (!onlineUsersMap.containsKey(loginId)) {
            onlineUsersMap.put(loginId, session);
        }

        Set<String> users = onlineUsersMap.keySet();
        System.out.println("Connected users: ");
        for (String user : users) {
            System.out.println(user);
        }

    }

    public synchronized void leave(String loginId) {
        System.out.println("Closing: "+onlineUsersMap.get(loginId).getRequestURI());
        onlineUsersMap.remove(loginId);
    }

    public synchronized static MessageUtil getRoom() {
        if (instance == null) {
            instance = new MessageUtil();
        }
        return instance;
    }

    private synchronized void addCustomerOrder(Long companyId, CustomerOrder order) {
        if (orderMap.containsKey(companyId)) {
            orderMap.get(companyId).add(order);
        } else {
            List<CustomerOrder> orderList = new ArrayList<CustomerOrder>();
            orderList.add(order);
            orderMap.put(companyId, orderList);
        }
    }

    public Set<String> getOnlineUsers() {
        Set<String> users = onlineUsersMap.keySet();
        return users;
    }

    public synchronized void handleCustomerOrder(Envelope envelope) {
        System.out.println(envelope);
        CustomerOrder order = envelope.getCustomerOrder();
        Login login = order.getCustomer();

        addCustomerOrder(login.getCompany().getId(), order);

        String sendTo = "8802782657";
        try {
            Session managerSession = onlineUsersMap.get(sendTo);
            Gson gson = new Gson();

            managerSession.getBasicRemote().sendText(gson.toJson(order));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
