package com.kroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kroll.constants.AppEnum;
import com.kroll.dao.LoginDAO;
import com.kroll.dao.CustomerOrderDAO;
import com.kroll.dao.ShoppingCartDAO;
import com.kroll.domain.Login;
import com.kroll.domain.CustomerOrder;
import com.kroll.domain.ShoppingCart;
import com.kroll.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerOrderDAO orderDAO;

    @Autowired
    private ShoppingCartDAO cartDAO;

    @Autowired
    private LoginDAO loginDAO;

    public OrderServiceImpl() {
		System.out.println("Initialized: "+this.getClass().getName());

		System.out.println("LoginDAO: "+loginDAO);
		System.out.println("OrderDAO "+orderDAO);
	}
    
    @Override
    public CustomerOrder create(CustomerOrder customerOrder) {
        
        ShoppingCart cart = cartDAO.findById(customerOrder.getCart().getId());
        Login customer = cart.getCustomer();

        customerOrder.setCustomer(customer);
        customerOrder.setCart(cart);
        customerOrder.setStatus(AppEnum.OrderStatus.GENERATED);
        orderDAO.create(customerOrder);

        
        cart.setStatus(AppEnum.ShoppingCartStatus.DETACHED);
        cartDAO.update(cart);

        customer.getPerson().setAddress(customerOrder.getAddress());
        customer.getPerson().setFullName(customerOrder.getReceiverName());
        customer.getPerson().setEmail(customerOrder.getEmail());
        loginDAO.update(customer);
        
        return customerOrder;
    }

    @Override
    public CustomerOrder update(CustomerOrder customerOrder) {
        return orderDAO.update(customerOrder);
    }

    @Override
    public CustomerOrder findById(long orderId) {
        return orderDAO.findById(orderId);
    }

    @Override
    public List<CustomerOrder> findAllByCustomerLoginId(String customerLoginId) {
        return orderDAO.findAllByCustomerLoginId(customerLoginId);
    }

    @Override
    public List<CustomerOrder> findAllByCompanyId(long companyId) {
        return orderDAO.findAllByCompanyId(companyId);
    }

}
