package com.yuntian.smartblog.util;

import com.yuntian.smartblog.model.entity.UserAccount;

/**
 * @Auther: yuntian
 * @Date: 2018/8/20 23:29
 * @Description:
 */
public interface TokenManager {


    /**
     * 创建token
     *
     * @param userInfo
     * @return
     */
    String getToken(UserAccount userInfo);

    /**
     * 刷新用户
     *
     * @param token
     */
    void refreshUserToken(String token);

    /**
     * 用户退出登陆
     *
     * @param token
     */
    void loginOut(String token);


    /**
     * 获取用户信息
     * @param token
     * @return
     */
    UserAccount getUserInfoByToken(String token);

}
