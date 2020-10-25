package com.example.bkwsp.base.service;

import com.example.bkwsp.base.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author han long
 * @since 2020-08-22
 */
public interface EmployeeService extends IService<Employee> {

    PageInfo<Employee> findPage(Employee model);
}
