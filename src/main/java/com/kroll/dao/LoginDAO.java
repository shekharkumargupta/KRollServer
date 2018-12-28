package com.kroll.dao;

import com.kroll.constants.AppEnum;
import com.kroll.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginDAO extends JpaRepository<Login, Long> {

    @Query("select l from Login l where l.company.id = ?1 and l.userType = ?2")
    public List<Login> findAllByUserType(long companyId, AppEnum.UserType userType);

    //public List<Login> findAllByProfession(String professionName);

    @Query("Select l from Login l where l.person.fullName like ?1 or l.person.email like ?2 or l.person.address.city like ?3 or l.person.mobileNumber like ?4")
    public List<Login> search(String searchString);

    @Query("select l from Login l where l.loginId = ?1")
    public Login findByLoginId(String loginId);

    @Query("select l from Login l where l.loginId = ?1 and l.password = ?2")
    public Login verifyUser(String loginId, String password);
}
