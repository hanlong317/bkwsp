package com.example.bkwsp.base.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author han long
 * @since 2020-08-22
 */
@Accessors(chain = true)
@TableName(value = "tm_employee")
public class Employee {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    @TableId
    private String userId;

    /**
     * 昵称
     */
    private String name;

    /**
     * 身份证号
     */
    private String idNumber;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 是否可用
     */
    private String isAvailable;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 账号
     */
    private String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
