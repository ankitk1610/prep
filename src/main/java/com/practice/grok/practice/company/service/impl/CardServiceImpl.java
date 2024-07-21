package com.practice.grok.practice.company.service.impl;

import com.practice.grok.practice.company.model.CardDetail;
import com.practice.grok.practice.company.model.Company;
import com.practice.grok.practice.company.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
public class CardServiceImpl implements CardService {

    RestTemplate restTemplate =  new RestTemplate();

    @Override
    public List<CardDetail> getCardsByCompanyAndUserId(String companyId, String userId) {

        ParameterizedTypeReference<List<CardDetail>> responseType=
                new ParameterizedTypeReference<>() {};

        try {
            ResponseEntity<List<CardDetail>> responseEntity = restTemplate.exchange("http://localhost:3000/companies/{id}/users/{id}/cards", HttpMethod.GET, HttpEntity.EMPTY,responseType,companyId,userId);
            return responseEntity.getBody();
        } catch (Exception ex) {
            log.error("Exception Occurred: {}", ex.getMessage());
        }
        return List.of();

    }
}
