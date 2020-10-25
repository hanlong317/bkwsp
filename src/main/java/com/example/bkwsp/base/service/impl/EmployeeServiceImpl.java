package com.example.bkwsp.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.bkwsp.base.entity.Employee;
import com.example.bkwsp.base.mapper.EmployeeMapper;
import com.example.bkwsp.base.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bkwsp.core.helper.WrapperHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author han long
 * @since 2020-08-22
 */
@Service
@Transactional
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public PageInfo<Employee> findPage(Employee model) {
        List<Employee> list = employeeMapper.selectList(new WrapperHelper<Employee>().genericQueryWrapper(model));
        return new PageInfo<Employee>(list);
    }
}
