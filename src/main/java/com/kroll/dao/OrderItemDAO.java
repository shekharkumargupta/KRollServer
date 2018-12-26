package com.kroll.dao;

import com.kroll.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemDAO extends JpaRepository<OrderItem, Long> {
}
