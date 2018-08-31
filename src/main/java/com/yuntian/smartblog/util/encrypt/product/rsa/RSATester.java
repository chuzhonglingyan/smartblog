package com.yuntian.smartblog.util.encrypt.product.rsa;

import com.yuntian.smartblog.util.encrypt.Base64Utils;

import org.apache.commons.codec.binary.Hex;

import java.util.Map;

/**
 * @ Author     ：guangleilei.
 * @ Date       ：Created in 11:01 2018/8/30
 * @ Description：${description}
 */
public class RSATester {

    static String publicKey;
    static String privateKey;

    static {
        try {
            Map<String, Object> keyMap = RSAUtils.genKeyPair();
            publicKey = RSAUtils.getPublicKey(keyMap);
            privateKey = RSAUtils.getPrivateKey(keyMap);

            System.err.println("初始化");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        System.err.println("公钥: \n\r" + publicKey);
        System.err.println("私钥： \n\r" + privateKey);
      //  test();
//        testSign();
        testHttpSign();
    }

    static void test() throws Exception {
        System.err.println("公钥加密——私钥解密");
        String source = "这是一行没有任何意义的文字，你看完了等于没看，不是吗？";
        System.out.println("\r加密前文字：\r\n" + source);
        String encodedStr=RSAUtils.encryptByPublicKeyToHexString(source,publicKey);
        System.out.println("加密后文字：\r\n" + encodedStr);
        System.out.println("解密后文字: \r\n" + RSAUtils.decryptByPrivateKey(encodedStr,privateKey));
    }

    static void testSign() throws Exception {
        System.err.println("私钥加密——公钥解密");
        String source = "这是一行测试RSA数字签名的无意义文字";
        System.out.println("原文字：\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);
        System.out.println("加密后：\r\n" + new String(encodedData));
        byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
        String target = new String(decodedData);
        System.out.println("解密后: \r\n" + target);
        System.err.println("私钥签名——公钥验证签名");
        String sign = RSAUtils.sign(encodedData, privateKey);
        System.err.println("签名:\r" + sign);
        boolean status = RSAUtils.verify(encodedData, publicKey, sign);
        System.err.println("验证结果:\r" + status);
    }

    static void testHttpSign() throws Exception {
        System.err.println("测试参数：私钥加密——公钥解密");
        String param = "id=1&name=张三";
        byte[] encodedData = RSAUtils.encryptByPrivateKey(param.getBytes(), privateKey);
        System.out.println("加密后：" + Base64Utils.encode(encodedData));

        byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
        System.out.println("解密后：" + new String(decodedData));

        String sign = RSAUtils.sign(encodedData, privateKey);
        System.err.println("测试参数签名：" + sign);

        boolean status = RSAUtils.verify(encodedData, publicKey, sign);
        System.err.println("签名验证结果：" + status);
    }
}
