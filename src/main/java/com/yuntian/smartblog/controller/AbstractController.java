package com.yuntian.smartblog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public abstract class AbstractController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private HttpServletRequest request;

    protected String getContextPath(){
        return request.getContextPath();
    }


}
