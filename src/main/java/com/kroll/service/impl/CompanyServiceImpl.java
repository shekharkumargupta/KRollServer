package com.kroll.service.impl;

import com.kroll.dao.CompanyDAO;
import com.kroll.domain.Company;
import com.kroll.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDAO companyDAO;

    @Override
    public Company create(Company company) {
        return companyDAO.save(company);
    }

    @Override
    public Company update(Company company) {
        return companyDAO.save(company);
    }

    @Override
    public Collection<Company> findAll() {
        return companyDAO.findAll();
    }

    @Override
    public Company findById(long companyId) {
        return companyDAO.findById(companyId).get();
    }
}
