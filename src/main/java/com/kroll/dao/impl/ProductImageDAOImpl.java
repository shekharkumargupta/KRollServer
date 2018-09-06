package com.kroll.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kroll.dao.ProductImageDAO;
import com.kroll.domain.Product;
import com.kroll.domain.ProductImage;

@Repository
public class ProductImageDAOImpl implements ProductImageDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public ProductImage create(ProductImage image) {
        getSession().save(image);
        Product product = (Product) getSession().get(Product.class, image.getProductId());
        product.addImage(image);
        getSession().update(product);
        return image;
    }

    public ProductImage update(ProductImage image) {
        getSession().merge(image);
        return image;
    }

    public ProductImage findById(long id) {
        String queryString = "select i from ProductImage i where i.id = " + id;
        Query query = getSession().createQuery(queryString);
        return (ProductImage) query.uniqueResult();
    }

    public ProductImage findByProductId(long productId) {
        String queryString = "select i from ProductImage i where i.productId = " + productId;
        Query query = getSession().createQuery(queryString);
        ProductImage image = (ProductImage) query.uniqueResult();
        return image;
    }

    @SuppressWarnings("unchecked")
    public List<ProductImage> findAll() {
        String queryString = "select i from ProductImage i";
        Query query = getSession().createQuery(queryString);
        return query.list();
    }
}
