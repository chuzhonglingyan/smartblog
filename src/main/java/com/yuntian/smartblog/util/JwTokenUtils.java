package com.yuntian.smartblog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @Auther: yuntian
 * @Date: 2018/8/20 23:23
 * @Description:
 */
public class JwTokenUtils {


    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 签名秘钥
     */
    public static final String SECRET = "yuleitianyu";

    public static final String ISSUER = "www.yuntian.com";
    public static final String SUBJECT = "chuzhonglingyan@163.com";
    public static final int JWT_TTL = 10000;  //millisecond 10秒

    /**
     * 生成token
     *
     * @param id 一般传入userName
     * @return
     */
    public static String createJwtToken(String id) {
        return createJwtToken(id, ISSUER, SUBJECT, JWT_TTL);
    }

    /**
     * 生成Token
     *
     * @param id        编号
     * @param issuer    该JWT的签发者，是否使用是可选的
     * @param subject   该JWT所面向的用户，是否使用是可选的；
     * @param ttlMillis 签发时间
     * @return token String
     */
    public static String createJwtToken(String id, String issuer, String subject, long ttlMillis) {

        // 签名算法 ，将对token进行签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成签发时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 通过秘钥签名JWT
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        // if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();

    }

    // Sample method to validate and read the JWT
    public static Claims parseJWT(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    public static void main(String[] args) {

        String token1 = JwTokenUtils.createJwtToken("admin");

        Date now = new Date(System.currentTimeMillis());
        System.out.println("cuurent:" +  now.toString());
        System.out.println("token1:" + token1);
        String admin1 = JwTokenUtils.parseJWT(token1).getId();
        System.out.println("name:" + admin1);
        try {
            Thread.sleep(JWT_TTL);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String admin2 = JwTokenUtils.parseJWT(token1).getId();
        System.out.println("name:" + admin2);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        String token2=JwTokenUtils.createJwtToken("admin");
//
//        System.out.println("token1:"+token1);
//        System.out.println("token2:"+token2);
//        System.out.println(StringUtils.equals(token1,token2));
//
//        try {
//            Thread.sleep(JWT_TTL);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        String token3=JwTokenUtils.createJwtToken("admin");
//        System.out.println("token3:"+token3);
//        System.out.println(StringUtils.equals(token2,token3));

    }
}
