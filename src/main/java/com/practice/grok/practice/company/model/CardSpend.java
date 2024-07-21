package com.practice.grok.practice.company.model;

import lombok.Data;

import java.util.List;

@Data
public class CardSpend {

    private String userName;
    private List<Card> cards;

    private double availableLimit;



}
