package com.practice.grok.practice.company.service;

import com.practice.grok.practice.company.model.CardDetail;

import java.util.List;

public interface CardService {

   List<CardDetail> getCardsByCompanyAndUserId(String companyId, String userId);
}

