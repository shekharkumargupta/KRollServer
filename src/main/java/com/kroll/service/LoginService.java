package com.kroll.service;

import java.util.List;

import com.kroll.constants.AppEnum;
import com.kroll.domain.Login;


public interface LoginService {

    public Login create(Login login);

    public Login update(Login login);

    public Login remove(Long id);

    public List<Login> findAll();

    public List<Login> search(String searchString);

    public Login findById(Long id);

    public Login findByLoginId(String loginId);

    public Login verifyUser(String loginId, String password);
    
    public List<Login> findAllByUserType(long companyId, AppEnum.UserType userType);
}
