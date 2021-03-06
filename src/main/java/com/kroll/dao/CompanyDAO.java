package com.kroll.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kroll.domain.Company;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDAO extends JpaRepository<Company, Long> {

}
