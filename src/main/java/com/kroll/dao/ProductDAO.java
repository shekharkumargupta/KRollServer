package com.kroll.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kroll.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Long>{

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
    public List<Product> findAll();
}
