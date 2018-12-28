package com.kroll.web.controller;

import com.kroll.constants.AppEnum;
import com.kroll.constants.SessionConstants;
import com.kroll.domain.Login;
import com.kroll.domain.Person;
import com.kroll.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    @Autowired
    private LoginService loginService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String test() {
        return "Test success!";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public Login create(@RequestBody Login login) {
        login = loginService.findByLoginId(login.getLoginId());
        if (login == null) {
            login = new Login();
            login.setLoginId(login.getLoginId());
            login = loginService.create(login);
        }
        return login;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public Login update(@RequestBody Person person) {
        log.info("Update: " + person);
        Login login = null;
        login = loginService.findByLoginId(person.getEmail());
        login.getPerson().setAddress(person.getAddress());
        login.getPerson().setEmail(person.getEmail());
        login.getPerson().setMobileNumber(person.getMobileNumber());

        login = loginService.update(login);

        return login;
    }

    @RequestMapping(value = "verify", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public Login verify(@RequestBody Login login) {
        log.info("Request: " + login);
        System.out.println("Request: "+login);
        login = loginService.create(login);
        System.out.println("Created Login: "+login);
        log.info("Created Login: "+login);
        return login;
    }

    @RequestMapping(value = "loggedInUser", method = RequestMethod.GET, produces = "application/json")
    public Login getLoggedInUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (Login) session.getAttribute(SessionConstants.LOGIN);
    }

    @RequestMapping(value = "findAll", method = RequestMethod.GET, produces = "application/json")
    public List<Login> findAll() {

        List<Login> loginList = null;
        loginList = loginService.findAll();
        for (Login login : loginList) {
            System.out.println(login);
        }
        return loginList;
    }

    @RequestMapping(value = "findByUserType/{companyId}/{userType}", method = RequestMethod.GET, produces = "application/json")
    public List<Login> findByUserType(
            @PathVariable(value = "companyId") long companyId,
            @PathVariable(value = "userType") int userType) {
        List<Login> loginList = null;
        loginList = loginService.findAllByUserType(companyId, AppEnum.UserType.values()[userType]);
        return loginList;
    }

    @RequestMapping(value = "search/{searchString}", method = RequestMethod.GET, produces = "application/json")
    public List<Login> search(@PathVariable(value = "searchString") String searchString) {
        List<Login> loginList = null;
        loginList = loginService.search(searchString);
        return loginList;
    }

    @RequestMapping(value = "findByLoginId/{loginId}", method = RequestMethod.GET, produces = "application/json")
    public Login findByLoginId(@PathVariable(value = "loginId") String loginId) {
        Login login = null;
        login = loginService.findByLoginId(loginId);
        System.out.println("findByLoginId: " + login);
        return login;
    }


    @RequestMapping(value = "findById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Login findById(@PathVariable(value = "id") long id){
        return loginService.findById(id);
    }
}
