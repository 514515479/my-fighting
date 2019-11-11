package com.kon.fighting.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description jwt生成token
 * 生成jks文件的命令：keytool -genkey -alias jwt -keyalg RSA -keysize 1024 -keystore jwt.jks -validity 365
 * 口令输入123456，其他直接回车，确认填写“是
 * @Author LK
 * @Date 2019/10/20 19:47
 * @Version V1.0
 **/
@Slf4j
public class JwtTokenUtils {

    /**
     * 测试JwtTokenUtil
     *
     * @param args
     */
    public static void main(String[] args) {
        String subject = "abc";
        int expirationSeconds = 60;
        Map<String, Object> claims = new HashMap<>();
        claims.put("a", "a");
        System.out.println(generateTokenByPrivateKey(subject, expirationSeconds, claims));
        System.out.println(parseTokenByPublicKey(generateTokenByPrivateKey(subject, expirationSeconds, claims)));
        System.out.println(getClaimsByPublicKey(generateTokenByPrivateKey(subject, expirationSeconds, claims)));

        System.out.println(generateTokenBySalt(subject, expirationSeconds, claims));
        System.out.println(parseTokenBySalt(generateTokenBySalt(subject, expirationSeconds, claims)));
        System.out.println(getClaimsBySalt(generateTokenBySalt(subject, expirationSeconds, claims)));
    }

    // 寻找证书文件
    private static InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("jwt.jks"); // 寻找证书文件
    private static PrivateKey privateKey = null;
    private static PublicKey publicKey = null;

    // 自定义盐(注意:这个盐不能太短，不然会报错：secret key byte array cannot be null or empty)
    private static String salt = "secret-key-kon";

    static { // 将证书文件里边的私钥公钥拿出来
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS"); // java key store 固定常量
            keyStore.load(inputStream, "123456".toCharArray());
            privateKey = (PrivateKey) keyStore.getKey("jwt", "123456".toCharArray()); // jwt 为 命令生成整数文件时的别名
            publicKey = keyStore.getCertificate("jwt").getPublicKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据私钥生成token
     *
     * @param subject           （主体信息）
     * @param expirationSeconds 过期时间（秒）
     * @param claims            自定义身份信息
     * @return
     */
    public static String generateTokenByPrivateKey(String subject, int expirationSeconds, Map<String, Object> claims) {
        if (claims == null) {
            claims = new HashMap<>();
        }
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     * 解析token, 获得subject中的信息
     *
     * @param token
     * @return
     */
    public static String parseTokenByPublicKey(String token) {
        String subject = null;
        Claims claims = getTokenBodyByPublicKey(token);
        if (claims != null) {
            subject = claims.getSubject();
        }
        return subject;
    }

    /**
     * 根据token获取自定义信息
     *
     * @param token
     * @return
     */
    public static Map<String, Object> getClaimsByPublicKey(String token) {
        Map<String, Object> claims = null;
        claims = getTokenBodyByPublicKey(token);
        return claims;
    }

    /**
     * 判断token是否过期
     *
     * @param token
     * @return
     */
    public static boolean isExpirationByPublicKey(String token) {
        return getTokenBodyByPublicKey(token).getExpiration().before(new Date());
    }

    /**
     * 根据公钥获取自定义身份信息
     *
     * @param token token
     * @return
     */
    private static Claims getTokenBodyByPublicKey(String token) {
        Claims body = null;
        try {
            body = Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.warn("无法根据公钥从{}中获取到token信息", token);
        }
        return body;
    }

    //****************************************************salt**********************************************************

    /**
     * 根据盐生成token
     *
     * @param subject           （主体信息）
     * @param expirationSeconds 过期时间（秒）
     * @param claims            自定义身份信息
     * @return
     */
    public static String generateTokenBySalt(String subject, int expirationSeconds, Map<String, Object> claims) {
        if (claims == null) {
            claims = new HashMap<>();
        }
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
                .signWith(SignatureAlgorithm.HS512, salt)
                .compact();
    }

    /**
     * 解析token, 获得subject中的信息
     *
     * @param token
     * @return
     */
    public static String parseTokenBySalt(String token) {
        String subject = null;
        Claims claims = getTokenBodyBySalt(token);
        if (claims != null) {
            subject = claims.getSubject();
        }
        return subject;
    }

    /**
     * 根据token获取自定义信息
     *
     * @param token
     * @return
     */
    public static Map<String, Object> getClaimsBySalt(String token) {
        Map<String, Object> claims = null;
        claims = getTokenBodyBySalt(token);
        return claims;
    }

    /**
     * 判断token是否过期
     *
     * @param token
     * @return
     */
    public static boolean isExpirationBySalt(String token) {
        return getTokenBodyBySalt(token).getExpiration().before(new Date());
    }


    /**
     * 根据自定义盐获取自定义身份信息
     *
     * @param token token
     * @return
     */
    private static Claims getTokenBodyBySalt(String token) {
        Claims body = null;
        try {
            body = Jwts.parser()
                    .setSigningKey(salt)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.warn("无法根据自定义盐从{}中获取到token信息", token);
        }
        return body;
    }

}
