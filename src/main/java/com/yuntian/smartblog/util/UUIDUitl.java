package com.yuntian.smartblog.util;

import org.apache.commons.lang.StringUtils;

import java.util.UUID;

/**
 * @Auther: yuntian
 * @Date: 2018/8/22 22:22
 * @Description:
 */
public class UUIDUitl {

    public static String getUUIDName(String name) {
        // 唯一 字符串  fsd-sfsdf-sfsd-sdfsd
        String uuid = UUID.randomUUID().toString().replace("-", "");

        if (StringUtils.isNotBlank(name)){
            return uuid + name;
        }
        return uuid;
    }


}
