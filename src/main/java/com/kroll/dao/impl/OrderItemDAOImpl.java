package com.kroll.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kroll.dao.OrderItemDAO;
import com.kroll.domain.OrderItem;

@Repository
public class OrderItemDAOImpl implements OrderItemDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public OrderItem create(OrderItem order) {
        getSession().saveOrUpdate(order);
        return order;
    }

    @Override
    public OrderItem update(OrderItem order) {
        getSession().update(order);
        return order;
    }

    @Override
    public OrderItem findById(long orderId) {
        return (OrderItem) getSession().get(OrderItem.class, orderId);
    }

}
