package com.kroll.dao;

import com.kroll.constants.AppEnum;
import com.kroll.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginDAO extends JpaRepository<Login, Long> {

    public List<Login> findAllByUserType(long companyId, AppEnum.UserType userType);

    //public List<Login> findAllByProfession(String professionName);

    public List<Login> search(String searchString);

    public Login findByLoginId(String loginId);

    public Login verifyUser(String loginId, String password);
}
