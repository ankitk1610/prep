package com.practice.grok.practice.company.model;

import lombok.Data;

@Data
public class CardInfo {

    private String cardNumber;
    private String CVV;
    private String spendLimit;
    private String balance;


    public String getLastFourDigits() {
        return cardNumber.substring( cardNumber.length()-4);
    }
}
