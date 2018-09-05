package com.yuntian.common.base.util;



import com.yuntian.common.base.filter.BodyReaderHttpServletRequestWrapper;
import com.yuntian.common.base.util.encrypt.use.MD5Util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: yuntian
 * @Date: 2018/8/27 22:14
 * @Description:
 */
public class SignUtil {

    public static final String SIGN = "sign";

    public static String generateParam(Map<String, Object> params)
            throws UnsupportedEncodingException {
        return generateParam(params, true);
    }


    public static String generateParam(Map<String, Object> params, boolean encode)
            throws UnsupportedEncodingException {
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuffer temp = new StringBuffer();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value) {
                valueString = String.valueOf(value);
            }
            if (encode) {
                temp.append(URLEncoder.encode(valueString, "UTF-8"));
            } else {
                temp.append(valueString);
            }
        }
        return temp.toString();
    }


    public static boolean verifySign(HttpServletRequest request) {
        String sign = request.getHeader(SIGN);
        Map<String, Object> params = new HashMap<>();
        if (request.getContentType() != null && request.getContentType().contains("application/x-www-form-urlencoded")) {
            Enumeration<?> pNames = request.getParameterNames();
            while (pNames.hasMoreElements()) {
                String pName = (String) pNames.nextElement();
                Object pValue = request.getParameter(pName);
                params.put(pName, pValue);
            }
        }
        if (request.getContentType() != null && request.getContentType().contains("application/json")) {
            try {
                params.putAll(JsonUtils.parseJSON2Map(new BodyReaderHttpServletRequestWrapper(request).getBodyString(request)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            return MD5Util.checkMD5(generateParam(params), sign);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 功能描述:
     *
     * @param: "signature>"加密签名内容 "timestamp">时间戳 "nonce">随机字符串  "appid"应用接入Id
     * @return:
     * @auther: yuntian
     * @date: 2018/8/29 22:06
     */
    public static boolean ValidateSignature(String signature, String timestamp, String nonce, String appid) {

        return true;
    }


}
