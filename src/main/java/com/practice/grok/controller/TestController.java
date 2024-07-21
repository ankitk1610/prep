package com.practice.grok.controller;

import com.practice.grok.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
public class TestController {


    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/test")
    public ResponseEntity<?> test() {
//      UserDTO user = restTemplate.getForEntity("http://localhost:3000/users", UserDTO.class).getBody();



      Map map = restTemplate.exchange("http://localhost:3000/users/{id}",HttpMethod.GET, HttpEntity.EMPTY, Map.class,1).getBody();

        return  ResponseEntity.ok(map);
    }

}
