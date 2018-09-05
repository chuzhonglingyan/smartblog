package com.yuntian.common.base.util;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @Auther: yuntian
 * @Date: 2018/9/1 18:18
 * @Description: https://blog.csdn.net/xiaoshiyiqie/article/details/52852158
 */
public class PatternDemo {

//    /**
//     * @param args
//     */
//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        boolean flag = passwordRegex(scan.next());
//        if (flag) {
//            System.out.println("符合校验");
//        } else {
//            System.out.println("不符合校验");
//        }
//    }

    /***
     * 校验密码强度
     * @param str
     * @return
     */
    public static boolean passwordRegex(String str) {
        boolean flag = false;
        flag = Pattern.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$", str);

        return flag;
    }

    /***
     * 校验中文
     * @param str
     * @return
     */
    public static boolean chineseRegex(String str) {
        boolean flag = true;
        flag = Pattern.matches("^[\\u4e00-\\u9fa5]{0,}$", str);
        return flag;
    }

    /***
     * 由数字、26个英文字母或下划线组成的字符串
     * @param str
     * @return
     */
    public static boolean pointRegex(String str) {
        boolean flag = true;
        flag = Pattern.matches("^\\w+$", str);
        return flag;
    }

    /***
     * 校验E-Mail 地址
     * @param str
     * @return
     */
    public static boolean emailRegex(String str) {
        boolean flag = true;
        flag = Pattern.matches("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?", str);
        return flag;
    }

    /***
     * 校验身份证号码
     * 下面是身份证号码的正则校验。15 或 18位。
     * @param str
     * @return
     */
    public static boolean idCardRegex(String str) {
        boolean flag = true;
        if (str.length() == 15) {
            flag = Pattern.matches("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$", str);
        } else if (str.length() == 18) {
            flag = Pattern.matches("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$", str);
        } else {
            flag = false;
        }

        return flag;
    }

    /***
     * 校验日期
     * “yyyy-mm-dd“ 格式的日期校验，已考虑平闰年。
     * @param str
     * @return
     */
    public static boolean yearRegex(String str) {
        boolean flag = true;
        flag = Pattern.matches("^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$", str);
        return flag;
    }

    /***
     * 校验金额
     * 金额校验，精确到2位小数。
     * @param str
     * @return
     */
    public static boolean amountRegex(String str) {
        boolean flag = true;
        flag = Pattern.matches("^[0-9]+(.[0-9]{2})?$", str);
        return flag;
    }

    /***
     * 校验手机号
     * 下面是国内 13、15、18开头的手机号正则表达式。（可根据目前国内收集号扩展前两位开头号码）
     * @param str
     * @return
     */
    public static boolean telRegex(String str) {
        boolean flag = true;
        flag = Pattern.matches("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", str);
        return flag;
    }

    /***
     * 判断IE的版本
     * @param str
     * @return
     */
    public static boolean ieVersionRegex(String str) {
        boolean flag = true;
        flag = Pattern.matches("^.*MSIE [5-8](?:\\.[0-9]+)?(?!.*Trident\\/[5-9]\\.0).*$", str);
        return flag;
    }

    /***
     * 校验IP-v4地址
     * @param str
     * @return
     */
    public static boolean ipV4Regex(String str) {
        boolean flag = true;
        flag = Pattern.matches("\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b", str);
        return flag;
    }

    /***
     * 校验IP-v6地址
     * @param str
     * @return
     */
    public static boolean ipV6Regex(String str) {
        boolean flag = true;
        flag = Pattern.matches("(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))", str);
        return flag;
    }
}
