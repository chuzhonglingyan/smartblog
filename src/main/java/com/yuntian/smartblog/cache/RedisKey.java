package com.yuntian.smartblog.cache;

/**
 * @Auther: yuntian
 * @Date: 2018/8/21 23:57
 * @Description:
 */
public class RedisKey {


    public static final String BACKEND_LOGIN_PREFIX = "backend_login_%s";


    public static final String TOKEN_PREFIX = "token_%s";

    public static final long BACKEND_LOGIN_EXPIRE = 3600 * 24; //1天


    public static final long TOKEN_EXPIRE = 3600 * 24 * 7; //7天


    public static String getBackendLoginkey(String id) {
        return String.format(BACKEND_LOGIN_PREFIX, id);
    }


    public static String getTokenKey(String id) {
        return String.format(TOKEN_PREFIX, id);
    }


}
