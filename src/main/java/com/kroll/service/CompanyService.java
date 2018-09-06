package com.kroll.service;

import java.util.Collection;

import com.kroll.domain.Company;

public interface CompanyService {

    public Company create(Company company);

    public Company update(Company company);

    public Collection<Company> findAll();

    public Company findById(long companyId);
}
