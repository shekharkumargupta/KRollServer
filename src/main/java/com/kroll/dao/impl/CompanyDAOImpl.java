package com.kroll.dao.impl;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kroll.dao.CompanyDAO;
import com.kroll.domain.Company;

@Repository
public class CompanyDAOImpl implements CompanyDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Company create(Company company) {
        getSession().save(company);
        return company;
    }

    public Company update(Company company) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Company findById(long companyId) {
        return (Company) getSession().get(Company.class, companyId);
    }

    @SuppressWarnings("unchecked")
    public Collection<Company> findAll() {
        Query query = getSession().createQuery("Select c from Company c");
        return query.list();
    }
}
