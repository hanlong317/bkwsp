package com.example.bkwsp.core.helper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.lang.reflect.Field;

public class WrapperHelper<T> {

    /**
     * 动态生成标准查询 Wrapper
     * String 类型为模糊查询
     * Long 类型为精确查询
     * @param object
     * @return QueryWrapper
     */
    public QueryWrapper<T> genericQueryWrapper(T object){

        QueryWrapper<T> queryWrapper = new QueryWrapper<T>();

        if (null != object) {
            for (Field field : object.getClass().getDeclaredFields()) {
                if (field.getType() == String.class) {
                    String value = (String) ReflectionHelper.invokeGetter(object, field.getName());
                    if (StringHelper.isNotBlank(value)){
                        queryWrapper.apply("lower(" + StringUtils.camelToUnderline(field.getName()) + ") like lower({0})", "%" + value + "%");
                    }
                }

                if (field.getType() == Long.class) {
                    Object value = ReflectionHelper.invokeGetter(object, field.getName());
                    if (null != value){
                        queryWrapper.eq(StringUtils.camelToUnderline(field.getName()), value);
                    }
                }

            }
        }

        return queryWrapper;
    }

    /**
     * 动态生成标准查询 Wrapper
     * String 类型为模糊查询
     * Long 类型为精确查询
     * @param object
     * @return QueryWrapper
     */
    public QueryWrapper<T> genericEqQueryWrapper(T object){

        QueryWrapper<T> queryWrapper = new QueryWrapper<T>();

        if (null != object) {
            for (Field field : object.getClass().getDeclaredFields()) {
                if (field.getType() == String.class) {
                    String value = (String) ReflectionHelper.invokeGetter(object, field.getName());
                    if (StringHelper.isNotBlank(value)){
                        queryWrapper.eq(StringUtils.camelToUnderline(field.getName()), value);
                    }
                }

                if (field.getType() == Long.class) {
                    Object value = ReflectionHelper.invokeGetter(object, field.getName());
                    if (null != value){
                        queryWrapper.eq(StringUtils.camelToUnderline(field.getName()), value);
                    }
                }

            }
        }

        return queryWrapper;
    }

}
