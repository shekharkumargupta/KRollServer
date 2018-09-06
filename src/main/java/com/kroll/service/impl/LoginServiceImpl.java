package com.kroll.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kroll.constants.AppEnum.UserType;
import com.kroll.constants.ApplicationConstants;
import com.kroll.constants.SessionConstants;
import com.kroll.dao.LoginDAO;
import com.kroll.dao.PersonDAO;
import com.kroll.domain.Login;
import com.kroll.domain.Person;
import com.kroll.service.LoginService;

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
		    personDAO.create(person);
		    
			login.setPassword(ApplicationConstants.DEFAULT_PASSWORD);
			login.setPerson(person);
			login = loginDAO.create(login);
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

		login = loginDAO.update(login);

		return login;

	}

	public Login verify(Login login) {
		System.out.println("Request: " + login);
		Login loginObj = loginDAO.findByLoginId(login.getLoginId());
		System.out.println("Database: " + loginObj);
		if (loginObj == null) {
			loginDAO.create(login);
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

	public List<Login> findByProfession(String profession) {
		List<Login> loginList = null;
		loginList = loginDAO.findAllByProfession(profession);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Login remove(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Login> findAllByProfession(String professionName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Login find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Login verifyUser(String loginId, String password) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public List<Login> findAllByUserType(long companyId, UserType userType) {
        return loginDAO.findAllByUserType(companyId, userType);
    }

	/*
	 * @GET
	 * 
	 * @Path("findOnlineUsers/{loginId}")
	 * 
	 * @Produces(value = "application/json") public Set<String>
	 * findOnlineUsers(@PathParam(value = "loginId") String loginId) { return
	 * ChatServerEndpoint.getOnlineUsers(); }
	 */

}
