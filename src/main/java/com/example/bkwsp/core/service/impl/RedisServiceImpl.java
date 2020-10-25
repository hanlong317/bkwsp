package com.example.bkwsp.core.service.impl;

import com.example.bkwsp.base.entity.Employee;
import com.example.bkwsp.core.service.RedisService;

import javax.servlet.http.HttpServletRequest;

public class RedisServiceImpl implements RedisService {


    @Override
    public String getCurrentPerson(HttpServletRequest request) {
        return "hanlong";
    }
}
