package com.example.bkwsp.core.helper;

import org.hibernate.validator.internal.util.StringHelper;

import java.lang.reflect.InvocationTargetException;

import static org.apache.commons.lang3.reflect.MethodUtils.invokeMethod;

public class ReflectionHelper {

    private static final String GETTER_PREFIX="get";

    /**
     * 调用get方法
     */
    public static Object invokeGetter(Object obj, String properName){
       try{
           String getterMethodName = GETTER_PREFIX + StringHelper.decapitalize(properName);
           return invokeMethod(obj,getterMethodName,new Class[]{},new Object[]{});
       }catch (Exception e){
            e.getMessage();
       }
        return null;

    }


}
