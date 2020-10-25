package com.example.bkwsp.base.service;

import java.util.Map;

public interface EmailService {

    void send(String freemarkFile, Map<String,Object> map,String email);
}
