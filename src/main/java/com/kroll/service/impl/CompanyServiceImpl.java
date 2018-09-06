package com.kroll.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kroll.dao.CompanyDAO;
import com.kroll.domain.Company;
import com.kroll.service.CompanyService;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDAO companyDAO;

    @Override
    public Company create(Company company) {
        return companyDAO.create(company);
    }

    @Override
    public Company update(Company company) {
        return companyDAO.update(company);
    }

    @Override
    public Collection<Company> findAll() {
        return companyDAO.findAll();
    }

    @Override
    public Company findById(long companyId) {
        return companyDAO.findById(companyId);
    }

}
