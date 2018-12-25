package com.kroll.service;

import java.util.Optional;

import com.kroll.domain.OrderItem;

public interface OrderItemService {

    public OrderItem create(OrderItem order);

    public OrderItem update(OrderItem order);

    public Optional<OrderItem> findById(long orderId);
}
