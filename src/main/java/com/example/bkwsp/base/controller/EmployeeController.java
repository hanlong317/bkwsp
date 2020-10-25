package com.example.bkwsp.base.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.bkwsp.base.entity.Employee;
import com.example.bkwsp.base.service.EmailService;
import com.example.bkwsp.base.service.EmployeeService;
import com.example.bkwsp.config.PageVo;
import com.example.bkwsp.core.jsonResponse.CommonResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author han long
 * @since 2020-08-22
 */
@RestController
@RequestMapping("/base/tm-employee")
public class EmployeeController {

    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);


    @Resource
    private EmployeeService employeeService;

    @Resource
    private EmailService emailService;

    @Value("${webActivatePath}")
    private String webActivatePath;

    @RequestMapping(value = "/batch/v1" , method = RequestMethod.POST)
    public PageInfo<Employee> page(@RequestBody PageVo<Employee> pageVo){
        int pageNum=pageVo.getPageNum();
        int pageSize=pageVo.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        if(StringUtils.isNotBlank(pageVo.getOrderBy())){
            PageHelper.orderBy(pageVo.getOrderBy());
        }
        PageInfo<Employee> pageInfo =  employeeService.findPage(pageVo.getModel());
        return pageInfo;
    }


    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public Employee register(@RequestBody Employee employee){
        employee.setIsAvailable("Y");
        String uuid = getUUID();
        QueryWrapper<Employee> queryWrapper=new QueryWrapper<Employee>();
        queryWrapper.eq("user_id",uuid);
        Employee one = employeeService.getOne(queryWrapper);
        if(one == null){
            employee.setUserId(uuid);
        }
        sendEmail(employee);
        employee.setIsAvailable("1");
        employeeService.save(employee);
        return employee;
    }


    @RequestMapping(value = "/login/{username}/{password}" , method = RequestMethod.GET)
    public ResponseEntity<CommonResponse> login(@PathVariable String username, @PathVariable String password){
        QueryWrapper<Employee>  wrapper = new QueryWrapper<Employee>();
        wrapper.eq("user_name",username);
        wrapper.eq("pass_word",password);
        List<Employee> list = employeeService.list(wrapper);
        if(list.size() >0 ){
            Employee employee=list.get(0);
            if("0".equals(employee.getIsAvailable())){
                return new ResponseEntity<CommonResponse>(CommonResponse.Create(list.get(0),CommonResponse.CODE_OK,"查询成功"), HttpStatus.OK);
            }
            if("1".equals(employee.getIsAvailable())){
                return new ResponseEntity<CommonResponse>(CommonResponse.Create(null,CommonResponse.CODE_ERROR,"请通过邮箱激活账号"), HttpStatus.OK);
            }
            return new ResponseEntity<CommonResponse>(CommonResponse.Create(null,CommonResponse.CODE_ERROR,"账号存在问题"), HttpStatus.OK);
        }
        wrapper = new QueryWrapper<Employee>();
        wrapper.eq("email",username);
        wrapper.eq("pass_word",password);
        list = employeeService.list(wrapper);
        if(list.size() >0){
            Employee employee=list.get(0);
            if("0".equals(employee.getIsAvailable())){
                return new ResponseEntity<CommonResponse>(CommonResponse.Create(list.get(0),CommonResponse.CODE_OK,"查询成功"), HttpStatus.OK);
            }
            if("1".equals(employee.getIsAvailable())){
                return new ResponseEntity<CommonResponse>(CommonResponse.Create(null,CommonResponse.CODE_ERROR,"请通过邮箱激活账号"), HttpStatus.OK);
            }
            return new ResponseEntity<CommonResponse>(CommonResponse.Create(null,CommonResponse.CODE_ERROR,"账号存在问题"), HttpStatus.OK);
        }else{
            return new ResponseEntity<CommonResponse>(CommonResponse.Create(null,CommonResponse.CODE_ERROR,"查询失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/activate/{userId}" , method = RequestMethod.GET)
    public String  activate(@PathVariable String userId){
        QueryWrapper<Employee> queryWrapper=new QueryWrapper<Employee>();
        queryWrapper.eq("user_id",userId);
        List<Employee> list = employeeService.list(queryWrapper);
        if(list.size() >0){
            if("1".equals(list.get(0).getIsAvailable())){
                list.get(0).setIsAvailable("0");
                employeeService.updateById(list.get(0));
                return "激活成功！";
            }
        }
        return "激活失败！";
    }


    @RequestMapping(value = "/checkUserName/{username}" , method = RequestMethod.GET)
    public ResponseEntity<CommonResponse>  checkUserName(@PathVariable String username){
        QueryWrapper<Employee> queryWrapper=new QueryWrapper<Employee>();
        queryWrapper.eq("user_name",username);
        List<Employee> list = employeeService.list(queryWrapper);
        if(list.size() >0){
            return new ResponseEntity<CommonResponse>(CommonResponse.Create(true,CommonResponse.CODE_OK,""), HttpStatus.OK);
        }
        return new ResponseEntity<CommonResponse>(CommonResponse.Create(false,CommonResponse.CODE_OK,""), HttpStatus.OK);
    }

    @RequestMapping(value = "/checkEmail/{email}" , method = RequestMethod.GET)
    public ResponseEntity<CommonResponse>  checkEmail(@PathVariable String email){
        QueryWrapper<Employee> queryWrapper=new QueryWrapper<Employee>();
        queryWrapper.eq("email",email);
        List<Employee> list = employeeService.list(queryWrapper);
        if(list.size() >0){
            return new ResponseEntity<CommonResponse>(CommonResponse.Create(true,CommonResponse.CODE_OK,""), HttpStatus.OK);
        }
        return new ResponseEntity<CommonResponse>(CommonResponse.Create(false,CommonResponse.CODE_OK,""), HttpStatus.OK);
    }

    //获取唯一userid号
    public String getUUID(){
        UUID uuid=UUID.randomUUID();
        return uuid.toString().substring(0,6);
    }

    //发送注册邮件
    public void sendEmail(Employee employee){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("name",employee.getName());
        map.put("userName",employee.getUserName());
        map.put("path",webActivatePath+"?userId="+employee.getUserId());
        emailService.send("zhuce.ftl",map,employee.getEmail());
    }

}
