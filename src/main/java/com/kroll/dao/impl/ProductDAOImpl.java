package com.kroll.dao.impl;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kroll.dao.ProductDAO;
import com.kroll.domain.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Product create(Product product) {
        getSession().save(product);
        return product;
    }

    @Override
    public Product update(Product product) {
        getSession().update(product);
        return product;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Product> findProductsByName(String productName) {
        Query query = getSession().createQuery("Select p from Product p where" + " p.name like ? and p.item =?")
                .setParameter(0, productName + "%").setParameter(1, false);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Product> findItemsByName(String itemName) {
        Query query = getSession().createQuery("Select p from Product p where" + " p.name like ? and p.item =?")
                .setParameter(0, itemName + "%").setParameter(1, true);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public Collection<Product> findAll() {
        Query query = getSession().createQuery("Select p from Product p");
        return query.list();
    }

    @Override
    public Product findById(long id) {
        return (Product) getSession().get(Product.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Product> findAllItems(long categoryId) {
        Query query = getSession()
                .createQuery("Select p from Product p LEFT JOIN p.items i where" + " p.item =? and i.id = ?")
                .setParameter(0, true).setParameter(1, categoryId);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Product> findAllProducts(long companyId) {
        Query query = getSession().createQuery("Select p from Product p where" + " p.item =? and p.company.id = ?")
                .setParameter(0, false).setParameter(1, companyId);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Product> findAllMasterProducts(long companyId) {
        Query query = getSession()
                .createQuery("Select p from Product p where" + " p.item =? and p.master = ? and p.company.id = ?")
                .setParameter(0, false).setParameter(1, true).setParameter(2, companyId);
        return query.list();
    }
}
