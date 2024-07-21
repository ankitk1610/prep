package com.practice.grok.practice.company.service;

import com.practice.grok.practice.company.model.Company;

import java.util.List;

public interface CompanyService {


    List<Company> getCompanies();

    Company getCompanyById(String id);
}
