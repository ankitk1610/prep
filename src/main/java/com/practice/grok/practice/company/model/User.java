package com.practice.grok.practice.company.model;

import lombok.Data;

@Data
public class User {

    private String createdAt;
    private String name;
    private String id;
    private String companyId;

    private Company company;

}