package com.kroll.service;

import com.kroll.domain.OrderItem;

public interface OrderItemService {

    public OrderItem create(OrderItem order);

    public OrderItem update(OrderItem order);

    public OrderItem findById(long orderId);
}
