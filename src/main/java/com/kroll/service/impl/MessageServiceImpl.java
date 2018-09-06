package com.kroll.service.impl;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.kroll.constants.ActionConstants;
import com.kroll.constants.AppEnum;
import com.kroll.domain.CustomerOrder;
import com.kroll.domain.Envelope;
import com.kroll.domain.Login;
import com.kroll.service.LoginService;
import com.kroll.service.MessageService;
import com.kroll.service.OrderService;
import com.kroll.util.ApplicationContextUtil;

@ServerEndpoint("/messageServerEndpoint/{loginId}")
public class MessageServiceImpl implements MessageService {

	private final int DEFAULT_COMPANY_ID = 1;


	@Autowired
	private OrderService orderService;
	@Autowired
	private LoginService loginService;
	
	
	//private static Set<Session> onlineUsers = Collections.synchronizedSet(new HashSet<Session>());
	private static Map<String, Session> onlineUsersMap = new HashMap<String, Session>();
	private static Map<Long, List<CustomerOrder>> orderMap = new HashMap<>();

	public MessageServiceImpl() {
		System.out.println("Initialized: "+this.getClass().getName());
	}
	
	@OnOpen
	public void handleOpen(@PathParam(value = "loginId") String loginId, Session userSession) throws IOException {
		userSession.getUserProperties().put("loginId", loginId);
		//onlineUsers.add(userSession);
		onlineUsersMap.put(loginId, userSession);

		// This block of code is just for testing. should be remove later.
		Set<String> users = onlineUsersMap.keySet();
		System.out.println("Connected users: ");
		for (String user : users) {
			System.out.println(user);
		}

		userSession.getBasicRemote().sendText("Hi " + loginId + " !");

		try {
			orderService = ApplicationContextUtil.getApplicationContext().getBean(OrderService.class);
			List<CustomerOrder> orderList = orderService.findAllByCompanyId(DEFAULT_COMPANY_ID);
			orderMap.put(new Long(1), orderList);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@OnMessage
	public void handleMessage(String messageString, Session userSession) throws IOException {
		System.out.println("MessageString: " + messageString);
		
		Map<String, Object> userProperties = userSession.getUserProperties();
		Set<String> keySet = userProperties.keySet();
		for (String key : keySet) {
			System.out.println(key + ": \t" + userProperties.get(key));
		}

		Gson gson = new Gson();
		Envelope envelope = gson.fromJson(messageString, Envelope.class);
		System.out.println("envelope: " + envelope);

		if (envelope.getMessageType() == ActionConstants.ORDER_GENERATED) {
			orderGenerated(envelope);
		}
		if(envelope.getMessageType() == ActionConstants.ORDER_RECEIVED){
			orderReceived(envelope);
		}
		if(envelope.getMessageType() == ActionConstants.ORDER_DISPATCHED){
			orderDispatch(envelope);
		}
		if(envelope.getMessageType() == ActionConstants.ORDER_ON_THE_WAY){
			orderOnTheWay(envelope);
		}

	}

	@OnClose
	public void handleClose(Session userSession) {
		String loginId = (String) userSession.getUserProperties().get("loginId");
		onlineUsersMap.remove(loginId);
	}

	public Set<String> getOnlineUsers() {
		Set<String> users = onlineUsersMap.keySet();
		return users;
	}

	private void orderGenerated(Envelope envelope) {
		
		loginService = ApplicationContextUtil.getApplicationContext().getBean(LoginService.class);
		orderService = ApplicationContextUtil.getApplicationContext().getBean(OrderService.class);
		
		System.out.println("LoginDAO: "+loginService);
		System.out.println("CustomerOrderDAO "+orderService);
		
		CustomerOrder order = envelope.getCustomerOrder();

		Login login = loginService.findByLoginId(envelope.getSenderLoginId());
		if(orderMap.containsKey(login.getCompany().getId())){
			orderMap.get(login.getCompany().getId()).add(order);
		}else{
			List<CustomerOrder> customerOrders = new ArrayList<>();
			customerOrders.add(order);
			orderMap.put(login.getCompany().getId(), customerOrders);
		}

		List<Login> managerList = loginService.findAllByUserType(login.getCompany().getId(), AppEnum.UserType.MANAGER);

		for (Login manager : managerList) {
			Session managerSession = onlineUsersMap.get(manager.getLoginId());

			Gson gson = new Gson();
			try {
				managerSession.getBasicRemote().sendText(gson.toJson(envelope));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	
	private void orderReceived(Envelope envelope) {
		loginService = ApplicationContextUtil.getApplicationContext().getBean(LoginService.class);
		orderService = ApplicationContextUtil.getApplicationContext().getBean(OrderService.class);
		
		System.out.println("LoginDAO: "+loginService);
		System.out.println("CustomerOrderDAO "+orderService);
		
		CustomerOrder order = envelope.getCustomerOrder();
		Login customer = loginService.findByLoginId(order.getCustomer().getLoginId());
		
		order.setStatus(AppEnum.OrderStatus.RECEIVED);
		orderService.update(order);

		Session customerSession = onlineUsersMap.get(customer.getLoginId());
		Gson gson = new Gson();
		try {
			customerSession.getBasicRemote().sendText(gson.toJson(envelope));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void orderDispatch(Envelope envelope) {
		loginService = ApplicationContextUtil.getApplicationContext().getBean(LoginService.class);
		orderService = ApplicationContextUtil.getApplicationContext().getBean(OrderService.class);
		
		System.out.println("LoginDAO: "+loginService);
		System.out.println("CustomerOrderDAO "+orderService);
		
		CustomerOrder order = envelope.getCustomerOrder();
		Login customer = loginService.findByLoginId(order.getCustomer().getLoginId());
		
		order.setStatus(AppEnum.OrderStatus.DISPATCHED);
		orderService.update(order);

		Session deliveryBoySession = onlineUsersMap.get(envelope.getReceiverLoginId());
		Session customerSession = onlineUsersMap.get(customer.getLoginId());
		Gson gson = new Gson();
		try {
			deliveryBoySession.getBasicRemote().sendText(gson.toJson(envelope));
			customerSession.getBasicRemote().sendText(gson.toJson(envelope));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void orderOnTheWay(Envelope envelope) {
		loginService = ApplicationContextUtil.getApplicationContext().getBean(LoginService.class);
		orderService = ApplicationContextUtil.getApplicationContext().getBean(OrderService.class);
		
		System.out.println("LoginDAO: "+loginService);
		System.out.println("CustomerOrderDAO "+orderService);
		
		CustomerOrder order = envelope.getCustomerOrder();
		Login customer = loginService.findByLoginId(order.getCustomer().getLoginId());
		
		order.setStatus(AppEnum.OrderStatus.ON_THE_WAY);
		orderService.update(order);

		Session customerSession = onlineUsersMap.get(customer.getLoginId()); 
		Gson gson = new Gson();
		
		//Send message to customer.
		try {
			customerSession.getBasicRemote().sendText(gson.toJson(envelope));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//Send message to managers.
		List<Login> managerList = loginService.findAllByUserType(customer.getCompany().getId(), AppEnum.UserType.MANAGER);
		for (Login manager : managerList) {
			Session managerSession = onlineUsersMap.get(manager.getLoginId());
			try {
				managerSession.getBasicRemote().sendText(gson.toJson(envelope));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		
	}
	
}
