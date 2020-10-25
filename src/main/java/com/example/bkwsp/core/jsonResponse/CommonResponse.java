package com.example.bkwsp.core.jsonResponse;

import lombok.Data;

@Data
public class CommonResponse {

    private Object data;

    private String status;

    private String message;

    public final static String SUCCESS = "success";
    public final static String FAILED = "success";

    public final  static String CODE_OK="200";
    public final  static String CODE_ERROR="500";


    public static CommonResponse Create(Object obj,String status,String message){
        CommonResponse commonResponse=new CommonResponse();
        commonResponse.setData(obj);
        commonResponse.setMessage(message);
        commonResponse.setStatus(status);
        return commonResponse;
    }

}
