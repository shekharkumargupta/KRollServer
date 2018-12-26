package com.kroll.dao;

import com.kroll.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartDAO extends JpaRepository<ShoppingCart, Long> {

    public ShoppingCart findByCustomerLoginId(String customerLoginId);

    public List<ShoppingCart> findAllCartByCustomerLoginId(String customerLoginId);

    public ShoppingCart findByOrderId(String orderId);
}
