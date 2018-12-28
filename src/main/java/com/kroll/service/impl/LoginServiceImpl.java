package com.kroll.service.impl;

import com.kroll.constants.AppEnum;
import com.kroll.constants.ApplicationConstants;
import com.kroll.constants.SessionConstants;
import com.kroll.dao.LoginDAO;
import com.kroll.dao.PersonDAO;
import com.kroll.domain.Login;
import com.kroll.domain.Person;
import com.kroll.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDAO loginDAO;

    @Autowired
    private PersonDAO personDAO;

    public LoginServiceImpl(){
        System.out.println("Initialized: "+this.getClass().getName());

        System.out.println("LoginDAO: "+loginDAO);
        System.out.println("PersonDAO "+personDAO);
    }

    public Login create(Login login) {

        Login loginObj = loginDAO.findByLoginId(login.getLoginId());
        if (loginObj == null) {
            Person person = new Person();
            person.setMobileNumber(login.getLoginId());
            personDAO.save(person);

            login.setPassword(ApplicationConstants.DEFAULT_PASSWORD);
            login.setPerson(person);
            login = loginDAO.save(login);
            return login;
        } else {
            return loginObj;
        }
    }

    public Login update(Person person) {
        System.out.println("Update: " + person);
        Login login = null;
        login = loginDAO.findByLoginId(person.getEmail());
        login.getPerson().setAddress(person.getAddress());
        login.getPerson().setEmail(person.getEmail());
        login.getPerson().setMobileNumber(person.getMobileNumber());

        login = loginDAO.save(login);

        return login;

    }

    public Login verify(Login login) {
        System.out.println("Request: " + login);
        Login loginObj = loginDAO.findByLoginId(login.getLoginId());
        System.out.println("Database: " + loginObj);
        if (loginObj == null) {
            loginDAO.save(login);
        } else {
            // This method will be called when we apply the password concept
            // in our app.
            // login = loginDAO.verifyUser(login.getLoginId(),
            // login.getPassword());
            login = loginDAO.findByLoginId(login.getLoginId());
        }
        return login;
    }

    public Login getLoggedInUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (Login) session.getAttribute(SessionConstants.LOGIN);
    }

    public List<Login> findAll() {
        List<Login> loginList = null;
        loginList = loginDAO.findAll();
        for (Login login : loginList) {
            System.out.println(login);
        }
        return loginList;
    }

    public List<Login> search(String searchString) {
        List<Login> loginList = null;
        loginList = loginDAO.search(searchString);
        return loginList;
    }

    public Login findByLoginId(String loginId) {
        Login login = null;
        login = loginDAO.findByLoginId(loginId);
        System.out.println("findByLoginId: " + login);
        return login;
    }

    @Override
    public Login update(Login login) {
        loginDAO.save(login);
        return login;
    }

    @Override
    public Login remove(Long id) {
        Login login = loginDAO.findById(id).get();
        loginDAO.deleteById(id);
        return login;
    }

    @Override
    public Login findById(Long id) {
        return loginDAO.findById(id).get();
    }

    @Override
    public Login verifyUser(String loginId, String password) {
        return loginDAO.verifyUser(loginId, password);
    }

    @Override
    public List<Login> findAllByUserType(long companyId, AppEnum.UserType userType) {
        return loginDAO.findAllByUserType(companyId, userType);
    }
}
