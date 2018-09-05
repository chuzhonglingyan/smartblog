package com.yuntian.smartblog.model.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: yuntian
 * @Date: 2018/8/22 21:36
 * @Description: 后台角色
 */
public class BackedOperater implements Serializable {


    private static final long serialVersionUID = 2657896033111645090L;
    private long id;
    private String userName;

    @JSONField(serialize = false)
    private String passWord;

    private String phoneNumber;
    private String headPicture;

    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date createTime;

    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date updateTime;

    private int state;

    private int role;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
