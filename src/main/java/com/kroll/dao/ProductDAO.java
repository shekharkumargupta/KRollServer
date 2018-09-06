package com.kroll.dao;

import java.util.Collection;

import com.kroll.domain.Product;

public interface ProductDAO {

    public Product create(Product product);

    public Product update(Product product);

    public Product findById(long id);

    public Collection<Product> findAllMasterProducts(long companyId);

    public Collection<Product> findAllItems(long categoryId);

    // Not so useful methods
    public Collection<Product> findAllProducts(long companyId);

    public Collection<Product> findProductsByName(String productName);

    public Collection<Product> findItemsByName(String itemName);

    /**
     * Don't use this method for listing all the products of particular company.
     * You should use either findAllProducts(long companyId) or
     * findAllMasterProducts(long companyId).
     * 
     * This method should only be used by Application Vendor's
     * 
     * @return
     */
    public Collection<Product> findAll();

}
