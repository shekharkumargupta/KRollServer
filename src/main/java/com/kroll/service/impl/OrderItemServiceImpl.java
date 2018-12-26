package com.kroll.service.impl;

import com.kroll.dao.OrderItemDAO;
import com.kroll.domain.OrderItem;
import com.kroll.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {


    @Autowired
    private OrderItemDAO orderItemDAO;

    @Override
    public OrderItem create(OrderItem order) {
        return orderItemDAO.save(order);
    }

    @Override
    public OrderItem update(OrderItem order) {
        return orderItemDAO.save(order);
    }

    @Override
    public OrderItem findById(long orderId) {
        return orderItemDAO.findById(orderId).get();
    }
}
