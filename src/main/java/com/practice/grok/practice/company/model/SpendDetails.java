package com.practice.grok.practice.company.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SpendDetails {
    private String companyName;
    private String currencyCode;
    private List<CardSpend> usersWithSpends = new ArrayList<>();
    private List<CardSpend> usersWithoutSpends = new ArrayList<>();

}
