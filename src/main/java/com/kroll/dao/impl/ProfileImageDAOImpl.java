package com.kroll.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kroll.dao.ProfileImageDAO;
import com.kroll.domain.Login;
import com.kroll.domain.ProfileImage;

@Repository
public class ProfileImageDAOImpl implements ProfileImageDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public ProfileImage create(ProfileImage image) {
        getSession().save(image);
        return image;
    }

    @Override
    public ProfileImage update(ProfileImage image) {
        getSession().update(image);
        return image;
    }

    @Override
    public ProfileImage findByLoginId(String loginId) {
        Login login = (Login) getSession().createQuery("select l from Login l where l.loginId =:loginId")
                .setParameter("loginId", loginId).uniqueResult();

        ProfileImage image = (ProfileImage) getSession()
                .createQuery("select p from ProfileImage p where p.person.id =:personId ")
                .setParameter("personId", login.getPerson().getId()).uniqueResult();
        return image;
    }
}
