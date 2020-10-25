package com.example.bkwsp.core.service;

import com.example.bkwsp.base.entity.Employee;

import javax.servlet.http.HttpServletRequest;

public interface RedisService {

    String getCurrentPerson(HttpServletRequest request);
}
