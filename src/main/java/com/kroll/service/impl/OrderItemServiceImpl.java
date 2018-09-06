package com.kroll.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kroll.dao.OrderItemDAO;
import com.kroll.domain.OrderItem;
import com.kroll.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemDAO orderItemDAO;

    @Override
    public OrderItem create(OrderItem order) {
        return orderItemDAO.create(order);
    }

    @Override
    public OrderItem update(OrderItem order) {
        return orderItemDAO.update(order);
    }

    @Override
    public OrderItem findById(long orderId) {
        return orderItemDAO.findById(orderId);
    }

}
