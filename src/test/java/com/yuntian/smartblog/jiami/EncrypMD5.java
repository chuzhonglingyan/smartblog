package com.yuntian.smartblog.jiami;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.yuntian.smartblog.jiami.FileHashUtil.hexChar;

/**
 * @ Author     ：guangleilei.
 * @ Date       ：Created in 9:51 2018/8/29
 * @ Description：${description}
 */
public class EncrypMD5 {


    public byte[] eccrypt(String info) throws NoSuchAlgorithmException{
        //根据MD5算法生成MessageDigest对象
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] srcBytes = info.getBytes();
        //使用srcBytes更新摘要
        md5.update(srcBytes);
        //完成哈希计算，得到result
        byte[] resultBytes = md5.digest();
        return resultBytes;
    }


    public static void main(String args[]) throws NoSuchAlgorithmException {
        String msg = "郭XX-精品相声技术";
        EncrypMD5 md5 = new EncrypMD5();
        byte[] resultBytes = md5.eccrypt(msg);

        System.out.println("密文是：" + toHexString(resultBytes));
        System.out.println("明文是：" + msg);
    }

    public static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
            sb.append(hexChar[b[i] & 0x0f]);
        }
        return sb.toString();
    }

}
