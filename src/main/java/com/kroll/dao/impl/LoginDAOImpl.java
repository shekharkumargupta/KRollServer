/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kroll.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kroll.constants.AppEnum.UserType;
import com.kroll.constants.ApplicationConstants;
import com.kroll.dao.LoginDAO;
import com.kroll.domain.Login;
import com.kroll.domain.Person;

/**
 * 
 * @author Ramesh
 */

@Repository
public class LoginDAOImpl implements LoginDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public LoginDAOImpl() {
        System.out.println("Initialized: " + this.getClass().getName());
    }

    public Login create(Login login) {
        login.setPassword(ApplicationConstants.DEFAULT_PASSWORD);
        Person person = new Person();
        person.setMobileNumber(login.getLoginId());
        login.setPerson(person);
        getSession().save(person);
        getSession().save(login);
        return login;
    }

    public Login update(Login login) {
        getSession().update(login);
        return login;
    }

    public Login remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Login find(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @SuppressWarnings("unchecked")
    public List<Login> findAll() {
        // Session session = getSession();
        List<Login> loginList = getSession().createQuery("Select l from Login l").list();
        return loginList;
    }

    public Login findByLoginId(String loginId) {
        Query query = getSession().createQuery("Select l from Login l where l.loginId = '" + loginId + "'");
        Login login = (Login) query.uniqueResult();
        return login;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Login> findAllByProfession(String professionName) {
        List<Login> loginList = getSession()
                .createQuery("Select l from Login l where l.person.profession like '%" + professionName + "%'").list();
        return loginList;
    }
    
    @Override
	public List<Login> findAllByUserType(long companyId, UserType userType) {
    	@SuppressWarnings("unchecked")
		List<Login> loginList = getSession()
                .createQuery("Select l from Login l where l.userType = ?")
                .setParameter(0, userType)
                .list();
        return loginList;
	}

    @SuppressWarnings("unchecked")
    @Override
    public List<Login> search(String searchString) {
        String queryString = "Select l from Login l where " + "l.person.fullName like ? or "
                + "l.person.email like ? or " + "l.person.profession like ? or " + "l.person.city like ? or "
                + "l.person.mobileNumber like ? or " + "l.person.jobProfile like ? or " + "l.person.keySkills like ? ";

        List<Login> loginList = getSession().createQuery(queryString).setParameter(0, "%" + searchString + "%")
                .setParameter(1, "%" + searchString + "%").setParameter(2, "%" + searchString + "%")
                .setParameter(3, "%" + searchString + "%").setParameter(4, "%" + searchString + "%")
                .setParameter(5, "%" + searchString + "%").setParameter(6, "%" + searchString + "%").list();
        return loginList;
    }

    public Login verifyUser(String loginId, String password) {
        Query query = getSession().createQuery(
                "Select l from Login l where  l.loginId = '" + loginId + "'" + " and l.password = '" + password + "' ");
        Login login = (Login) query.uniqueResult();
        return login;
    }
}
