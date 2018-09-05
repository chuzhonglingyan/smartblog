package com.yuntian.smartblog.controller;


import com.yuntian.common.base.cache.RedisKey;
import com.yuntian.common.base.cache.RedisUtil;
import com.yuntian.common.base.controller.AbstractController;
import com.yuntian.common.base.model.vo.Result;
import com.yuntian.common.base.util.CookieUtils;
import com.yuntian.common.base.util.UUIDUitl;
import com.yuntian.smartblog.constants.BackedUserConstants;
import com.yuntian.smartblog.constants.CookieConstant;
import com.yuntian.smartblog.interceptor.BackendLoginInterceptor;
import com.yuntian.smartblog.model.entity.BackedOperater;
import com.yuntian.smartblog.service.BackedOperaterService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class BackedOperaterController extends AbstractController {


    @Resource
    BackedOperaterService backedOperaterService;

    @Resource
    RedisUtil redisUtil;


    @RequestMapping("login")
    public String loginPage() {
        return "login";
    }


    @RequestMapping("gotoLogin")
    public String gotoLogin() {
        return "login";
    }


    @RequestMapping("register")
    public String registerPage() {
        return "register";
    }


    /**
     * 功能描述:
     *
     * @param: 登录，使用POST，传输数据
     * @return:
     * @auther: yuntian
     * @date: 2018/8/19 19:49
     */
    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    @ResponseBody
    public Result loginPost(BackedOperater backedOperater, HttpServletResponse response, HttpSession session) {
        Result result = backedOperaterService.findBackedOperaterByNameAndPassWord(backedOperater);
        String loginCookie = UUIDUitl.getUUIDName(String.valueOf(backedOperater.getId()));

        //设置reids缓存用户信息
        redisUtil.set(RedisKey.getBackendLoginkey(loginCookie), result.getData(), RedisKey.BACKEND_LOGIN_EXPIRE);
        //设置cookie
        CookieUtils.set(response, CookieConstant.BACKEND_LOGIN_COOKIE, loginCookie, CookieConstant.BACKEND_LOGIN_EXPIRE);
        session.setAttribute(BackendLoginInterceptor.SESSION_KEY, result.getData()); //设置session sessionid第一次产生
        return result;
    }


    /**
     * 功能描述:
     *
     * @param: 注册用户，使用POST，传输数据
     * @return:
     * @auther: yuntian
     * @date: 2018/8/19 19:49
     */
    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    @ResponseBody
    public Result registerPost(BackedOperater backedOperater) {
        Result result = backedOperaterService.insertBackedOperater(backedOperater);
        return result;
    }


    /**
     * 功能描述:
     *
     * @param: 退出登录
     * @return:
     * @auther: yuntian
     * @date: 2018/8/20 23:09
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        //查询cookie
        Cookie cookie = CookieUtils.get(request, CookieConstant.BACKEND_LOGIN_COOKIE);
        if (cookie != null) {
            redisUtil.remove(String.format(RedisKey.BACKEND_LOGIN_PREFIX, cookie.getValue()));
        }
        CookieUtils.set(response, CookieConstant.BACKEND_LOGIN_COOKIE, "");
        session.removeAttribute(BackedUserConstants.SESSION_KEY);
        return "redirect:/login";
    }


}
