package com.kroll.dao;

import java.util.List;

import com.kroll.domain.Person;

public interface PersonDAO {

    public Person create(Person person);
    public Person update(Person person);
    public Person findById(long personId);
    public List<Person> findAll();
}
