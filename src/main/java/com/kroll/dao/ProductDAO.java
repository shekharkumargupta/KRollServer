package com.kroll.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kroll.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Long>{

    @Query("Select p from Product p where p.item =false and p.master = true and p.company.id = ?1")
    public Collection<Product> findAllMasterProducts(long companyId);

    @Query("Select p from Product p LEFT JOIN p.items i where p.item =true and i.id = ?1")
    public Collection<Product> findAllItems(long categoryId);

    @Query("Select p from Product p where p.item = false and p.company.id = ?1")
    public Collection<Product> findAllProducts(long companyId);

    public Collection<Product> findProductsByName(String productName);

    @Query("Select p from Product p where p.name = ?1 and p.item = true")
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
