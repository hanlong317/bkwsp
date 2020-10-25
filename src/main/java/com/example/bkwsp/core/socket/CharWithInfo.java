package com.example.bkwsp.core.socket;

import com.example.bkwsp.base.entity.Employee;
import lombok.Data;

@Data
public class CharWithInfo {
    private Long id;
    private String type;
    private String tid;
    private String data;
    private String userId;
    private String name;



}
