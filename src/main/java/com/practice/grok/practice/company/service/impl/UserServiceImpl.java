package com.practice.grok.practice.company.service.impl;

import com.practice.grok.practice.company.model.Company;
import com.practice.grok.practice.company.model.User;
import com.practice.grok.practice.company.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
public class UserServiceImpl implements UserService {

   private RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<User> getUsersByCompanyId(String companyId) {


        ParameterizedTypeReference<List<User>> responseType=
                new ParameterizedTypeReference<>() {
                };
        try {

            ResponseEntity<List<User>> responseEntity = restTemplate.exchange("http://localhost:3000/companies/{id}/users", HttpMethod.GET, HttpEntity.EMPTY, responseType, companyId);
            return responseEntity.getBody();

        }catch (RestClientException ex) {
            log.error("Client Exception Occurred : {}" , ex.getMessage());
        }
        catch (Exception ex) {
            log.error("Exception Occurred during fetching Users for companyID: {} :  {}" , companyId ,ex.getMessage());
        }

            return List.of();
    }
}
