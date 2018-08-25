package com.yuntian.smartblog;

import com.alibaba.fastjson.JSON;
import com.yuntian.smartblog.base.Result;
import com.yuntian.smartblog.model.entity.UserAccount;
import com.yuntian.smartblog.service.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    UserService userService;

    @Test
    public  void testinsertUser(){

        UserAccount userAccount=new UserAccount();
        userAccount.setUserName("哈哈共和国金海环境");
        userAccount.setPassWord("123456");
        userService.insertUser(userAccount);
    }


    @Test
    public  void selectUserList(){
        Result list=userService.selectUserList(0,10);
        logger.debug(JSON.toJSONString(list));
    }

    @Test
    public  void selectUserList2(){
        Result list=userService.selectUserListByRedis(String.valueOf(0),String.valueOf(10));
        logger.debug(JSON.toJSONString(list));
    }
}
