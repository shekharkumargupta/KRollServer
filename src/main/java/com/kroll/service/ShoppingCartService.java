package com.kroll.service;

import java.util.List;

import com.kroll.domain.ShoppingCart;

public interface ShoppingCartService {

    public ShoppingCart create(ShoppingCart cart);

    public ShoppingCart update(ShoppingCart cart);
    
    public ShoppingCart addItem(String loginId, long itemId, int qty);

    public ShoppingCart remove(ShoppingCart cart);

    public ShoppingCart findById(long shoppingCartId);

    public ShoppingCart findByCustomerLoginId(String customerLoginId);
    
    public List<ShoppingCart> findAllCartByCustomerLoginId(String customerLoginId);

    public ShoppingCart findByOrderId(Long orderId);
}
