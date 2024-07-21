package com.practice.grok.practice.company.service;

import com.practice.grok.practice.company.model.User;

import java.util.List;

public interface UserService {


    List<User> getUsersByCompanyId(String companyId);
}
