package com.kroll.service;

import java.util.List;

import com.kroll.domain.ProductImage;

public interface ProductImageService {

    public ProductImage create(ProductImage image);

    public ProductImage update(ProductImage image);

    public ProductImage findById(long id);

    public ProductImage findByProductId(long id);

    public List<ProductImage> findAll();
}
