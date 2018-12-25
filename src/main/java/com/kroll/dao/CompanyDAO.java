package com.kroll.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kroll.domain.Company;


public interface CompanyDAO extends JpaRepository<Company, Long> {

}
