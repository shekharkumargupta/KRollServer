package com.kroll.seed;


import com.kroll.constants.AppEnum;
import com.kroll.constants.ApplicationConstants;
import com.kroll.domain.Login;
import com.kroll.domain.Person;
import com.kroll.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class SeedUser {

    public static void createLogin(String personName, String emailId, String mobileNumber, AppEnum.UserType userType) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Person person = new Person();
        person.setFullName(personName);
        person.setEmail(emailId);

        Login login = new Login();
        login.setPassword(ApplicationConstants.DEFAULT_PASSWORD);
        login.setLoginId(mobileNumber);
        login.setUserType(userType);
        login.setActive(true);
        login.setPerson(person);

        session.save(login);
        session.getTransaction().commit();
        session.close();
    }

    public static void findAllLogins() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        List<Login> logins = session.createQuery("select l from Login l").list();
        for (Login login : logins) {
            System.out.println(login);
        }
    }

    public static void main(String args[]) {
        createLogin("Ramesh Kumar", "ramesh@gmail.com", "9990919840", AppEnum.UserType.MANAGER);
        createLogin("Shekhar Kumar", "shekhar@gmail.com", "8802782657", AppEnum.UserType.DELIVERY_BOY);

        createLogin("Shravan Kumar Gupta", "skg@gmail.com", "8802736199", AppEnum.UserType.CUSTOMER);
        createLogin("Sujit Kumar", "sujit@gmail.com", "9868351070", AppEnum.UserType.CUSTOMER);

        findAllLogins();
    }
}
