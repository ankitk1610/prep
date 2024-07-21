package com.practice.grok.practice.company.service;

import com.practice.grok.practice.company.model.*;
import com.practice.grok.practice.company.service.impl.CardServiceImpl;
import com.practice.grok.practice.company.service.impl.CompanyServiceImpl;
import com.practice.grok.practice.company.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class Test {

    public static void main(String[] args) {
        CompanyService companyService = new CompanyServiceImpl();
        UserService userService = new UserServiceImpl();
        CardService cardService = new CardServiceImpl();


        List<Company> companies = companyService.getCompanies().stream().map(
                t -> companyService.getCompanyById(t.getId())
        ).toList();

        log.info("Companies {}", companies);


        List<User> allUsers = new ArrayList<>();

        for (Company c : companies) {
            List<User> users = userService.getUsersByCompanyId(c.getId());
            allUsers.addAll(users);
        }

        Map<String, String> userComapnyNameMap = allUsers.stream().collect(Collectors.toMap(User::getName, t -> t.getCompany().getName()));


        List<CardSpend> cardSpendsForAllUsers = allUsers.stream().map(

                user -> {
                    List<CardDetail> cardDetails = cardService.getCardsByCompanyAndUserId(user.getCompanyId(), user.getId());


                    Double totalBalance = cardDetails.stream().map(t -> Double.parseDouble(t.getCardInfo().getBalance()) - Double.parseDouble(t.getCardInfo().getSpendLimit())).reduce((a, b) -> a + b).get();

                    List<Card> cardDigitDetails = cardDetails.stream().map(t -> new Card(t.getCardInfo().getLastFourDigits())).collect(Collectors.toUnmodifiableList());

                    CardSpend cardSpend = new CardSpend();

                    cardSpend.setUserName(user.getName());
                    cardSpend.setAvailableLimit(totalBalance > 0 ? totalBalance : 0);
                    cardSpend.setCards(cardDigitDetails);

                    return cardSpend;


                }).collect(Collectors.toUnmodifiableList());

        log.info("{}", cardSpendsForAllUsers);


        List<SpendDetails> allSpendDetails = cardSpendsForAllUsers.stream().map(t -> {

            SpendDetails spendDetails = new SpendDetails();

            spendDetails.setCompanyName(userComapnyNameMap.get(t.getUserName()));
            if (t.getAvailableLimit() < 0) {
                spendDetails.getUsersWithoutSpends().add(t);
            } else {
                spendDetails.getUsersWithSpends().add(t);
            }

            return spendDetails;
        }).collect(Collectors.toUnmodifiableList());


        log.info("All Spends {}", allSpendDetails);

    }


}
