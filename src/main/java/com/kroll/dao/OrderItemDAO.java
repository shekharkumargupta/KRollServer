package com.kroll.dao;

import com.kroll.domain.OrderItem;

public interface OrderItemDAO {

    public OrderItem create(OrderItem order);

    public OrderItem update(OrderItem order);

    public OrderItem findById(long orderId);

}
