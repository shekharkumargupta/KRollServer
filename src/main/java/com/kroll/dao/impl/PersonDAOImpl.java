package com.kroll.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kroll.dao.PersonDAO;
import com.kroll.domain.Person;

@Repository
public class PersonDAOImpl implements PersonDAO{

    
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    
    
    @Override
    public Person create(Person person) {
        getSession().save(person);
        return person;
    }

    @Override
    public Person update(Person person) {
       getSession().update(person);
       return person;
    }

    @Override
    public Person findById(long personId) {
        return (Person) getSession().get(Person.class, personId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Person> findAll() {
        Query query = getSession().createQuery("Select p from Person p");
        return query.list();
    }

}
