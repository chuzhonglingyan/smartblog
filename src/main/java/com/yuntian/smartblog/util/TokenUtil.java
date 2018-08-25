package com.yuntian.smartblog.util;

/**
 * @Auther: yuntian
 * @Date: 2018/8/23 23:27
 * @Description:
 */
public class TokenUtil {

    /**
     * 功能描述:
     *
     * @param: 创建token
     * @return:
     * @auther: yuntian
     * @date: 2018/8/23 23:28
     */
    public static String createToken(String name) {
        return UUIDUitl.getUUIDName(name);
    }


}
