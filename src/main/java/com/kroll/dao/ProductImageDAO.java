package com.kroll.dao;

import com.kroll.domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageDAO extends JpaRepository<ProductImage, Long> {

    public ProductImage findByProductId(long id);
}
