package com.example.bkwsp.base.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.bkwsp.base.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author han long
 * @since 2020-08-22
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

    List<Employee> find(Employee model);
}
