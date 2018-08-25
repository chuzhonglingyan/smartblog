package com.yuntian.smartblog.controller.mobile;

import com.yuntian.smartblog.annotation.LoginRequired;
import com.yuntian.smartblog.base.Result;
import com.yuntian.smartblog.cache.RedisUtil;
import com.yuntian.smartblog.controller.AbstractController;
import com.yuntian.smartblog.interceptor.TokenInterceptor;
import com.yuntian.smartblog.model.entity.UserAccount;
import com.yuntian.smartblog.service.UserService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: yuntian
 * @Date: 2018/8/19 23:47
 * @Description:
 */
@RestController
@RequestMapping("/api")
public class UserMoblieController extends AbstractController {

    @Resource
    UserService userService;


    /**
     * 功能描述:
     *
     * @param: 登录，使用POST，传输数据
     * @return:
     * @auther: yuntian
     * @date: 2018/8/19 19:49
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @LoginRequired(value = false)
    public Result loginPost(@RequestBody UserAccount user) {
        Result result = userService.login(user);
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
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @LoginRequired(value = false)
    public Result registerPost(UserAccount user) {
        Result result = userService.insertUser(user);
        return result;
    }


    /**
     * 功能描述:
     *
     * @param: 获取用户列表，使用POST，传输数据
     * @return:
     * @auther: yuntian
     * @date: 2018/8/19 19:49
     */
    @RequestMapping(value = "/getUserList/{pageNum}/{pageSize}")
    @LoginRequired(value = false)
    public Result getUserList(@PathVariable(name = "pageNum", required = false) String pageNum,
                              @PathVariable(name = "pageSize", required = false) String pageSize) {
        Result result = userService.selectUserList(pageNum, pageSize);
        return result;
    }

    /**
     * 功能描述:
     *
     * @param: 获取用户列表，需要用户权限
     * @return:
     * @auther: yuntian
     * @date: 2018/8/19 19:49
     */
    @RequestMapping(value = "/getUserListByToken/{pageNum}/{pageSize}")
    public Result getUserListByToken(@PathVariable(name = "pageNum", required = false) String pageNum,
                                     @PathVariable(name = "pageSize", required = false) String pageSize) {
        Result result = userService.selectUserList(pageNum, pageSize);
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
    public Result logout(HttpServletRequest request) {
        Result result=userService.loginOut(request.getHeader(TokenInterceptor.ACCESS_TOKEN));
        return result;
    }


}
