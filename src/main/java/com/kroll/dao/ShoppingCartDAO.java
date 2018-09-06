package com.kroll.dao;

import java.util.List;

import com.kroll.domain.ShoppingCart;

public interface ShoppingCartDAO {

    public ShoppingCart create(ShoppingCart cart);

    public ShoppingCart update(ShoppingCart cart);

    public ShoppingCart remove(ShoppingCart cart);

    public ShoppingCart findById(long shoppingCartId);

    public ShoppingCart findByCustomerLoginId(String customerLoginId);

    public List<ShoppingCart> findAllCartByCustomerLoginId(String customerLoginId);

    public ShoppingCart findByOrderId(String orderId);
}
