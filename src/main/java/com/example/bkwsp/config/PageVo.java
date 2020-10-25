package com.example.bkwsp.config;


import lombok.Data;

/**
 * 分页实体
 * @param <T>
 */
@Data
public class PageVo<T> {
    private int pageNum;
    private int PageSize;
    private T model;
    private String orderBy;

}
