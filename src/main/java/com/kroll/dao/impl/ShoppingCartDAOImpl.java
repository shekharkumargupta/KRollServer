package com.kroll.dao.impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kroll.constants.AppEnum;
import com.kroll.dao.ShoppingCartDAO;
import com.kroll.domain.CustomerOrder;
import com.kroll.domain.OrderItem;
import com.kroll.domain.Product;
import com.kroll.domain.ShoppingCart;

@Repository
public class ShoppingCartDAOImpl implements ShoppingCartDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public ShoppingCart create(ShoppingCart cart) {
        for (OrderItem item : cart.getOrderItems()) {
            getSession().save(item);
        }
        getSession().save(cart);
        return cart;
    }

    @Override
    public ShoppingCart update(ShoppingCart cart) {
        getSession().update(cart);
        return cart;
    }

    @Override
    public ShoppingCart remove(ShoppingCart cart) {
        cart.setStatus(AppEnum.ShoppingCartStatus.DETACHED);
        getSession().update(cart);
        return cart;
    }

    @SuppressWarnings("unchecked")
    public Collection<Product> findAll() {
        Query query = getSession().createQuery("Select p from Product p");
        return query.list();
    }

    @Override
    public ShoppingCart findById(long shoppingCartId) {
        return (ShoppingCart) getSession().get(ShoppingCart.class, shoppingCartId);
    }

    @Override
    public ShoppingCart findByCustomerLoginId(String customerLoginId) {
        Query query = getSession()
                .createQuery("Select s from ShoppingCart s where s.customer.loginId = ? and s.status = ?")
                .setParameter(0, customerLoginId).setParameter(1, AppEnum.ShoppingCartStatus.PERSISTENCE);
        return (ShoppingCart) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ShoppingCart> findAllCartByCustomerLoginId(String customerLoginId) {
        Query query = getSession().createQuery("Select s from ShoppingCart s where s.customer.loginId = ?")
                .setParameter(0, customerLoginId);
        return query.list();
    }

    @Override
    public ShoppingCart findByOrderId(String orderId) {
        CustomerOrder customerOrder = (CustomerOrder) getSession().get(CustomerOrder.class, orderId);
        return customerOrder.getCart();
    }

}
