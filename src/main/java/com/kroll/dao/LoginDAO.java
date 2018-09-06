package com.kroll.dao;

import java.util.List;

import com.kroll.constants.AppEnum;
import com.kroll.domain.Login;

public interface LoginDAO {

    public Login create(Login login);

    public Login update(Login login);

    public Login remove(Long id);

    public List<Login> findAll();
    
    public List<Login> findAllByUserType(long companyId, AppEnum.UserType userType);

    public List<Login> findAllByProfession(String professionName);

    public List<Login> search(String searchString);

    public Login find(Long id);

    public Login findByLoginId(String loginId);

    public Login verifyUser(String loginId, String password);
}
