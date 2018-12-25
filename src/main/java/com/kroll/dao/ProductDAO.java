package com.kroll.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kroll.domain.Product;

public interface ProductDAO extends JpaRepository<Product, Long>{

}
