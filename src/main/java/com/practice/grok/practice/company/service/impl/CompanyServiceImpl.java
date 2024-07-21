package com.practice.grok.practice.company.service.impl;

import com.practice.grok.practice.company.model.Company;
import com.practice.grok.practice.company.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {

//    @Autowired
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Company> getCompanies() {

        List<Company> companies = new ArrayList<>();

        ParameterizedTypeReference<List<Company>> responseType=
                new ParameterizedTypeReference<>() {
                };
        try {
            ResponseEntity<List<Company>> responseEntity = restTemplate.exchange("http://localhost:3000/companies", HttpMethod.GET, HttpEntity.EMPTY,responseType);
            companies = responseEntity.getBody();
            return companies;
        } catch (Exception ex) {
            log.error("Exception Occurred: {}", ex.getMessage());
        }
        return List.of();

    }

    @Override
    public Company getCompanyById(String id) {
        try {
            ResponseEntity<Company> responseEntity = restTemplate.exchange("http://localhost:3000/company/{id}", HttpMethod.GET, HttpEntity.EMPTY, Company.class, id);
            return responseEntity.getBody();
        } catch (Exception ex) {
            log.error("Exception Occurred: {}", ex.getMessage());
        }

        return null;

    }
}
