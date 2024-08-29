package com.example.demo.utils;

import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
@Data
public class JwtUtil implements Serializable {

    /**
     * 创建 JWT
     * @param subject 主题
     * @param issue 发行者
     * @param claim 用户信息
     * @param ttlMillis 过期时间
     * @return
     */
    public static String createJWT(String subject, String issue, Object claim, long ttlMillis) {
        // 获取当前时间戳
        long nowMillis = System.currentTimeMillis();
        // 计算超时时间
        long expireMillis = nowMillis + ttlMillis;

        String result = Jwts.builder()
                .claim("user", claim) // 身份信息或其他要传递的数据内容
                .setSubject(subject) // 设置主题
                .setIssuer(issue) // 发行者
                .setId(issue) // jwtID
                .setExpiration(new Date(expireMillis)) // 超时时间
                .signWith(getSignatureAlgorithm(), getAuthKey()) // 加密方式和密钥
                //.compressWith(CompressionCodecs.DEFLATE) // 压缩方式（注意使用这种压缩方式则无法在 jwt.io 上解析）
                .compact(); // 压缩
        return result;
    }

    /**
     * 解析 JWT
     * @param jwt
     * @return
     */
    public static Jws<Claims> pareseJWT(String jwt) {
        Jws<Claims> claims;
        try {
            claims = Jwts.parser().setSigningKey(getAuthKey())
                    .parseClaimsJws(jwt);
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }


    // 加密方式
    private static SignatureAlgorithm getSignatureAlgorithm() {
        return SignatureAlgorithm.HS256;
    }

    // 密钥
    private static String getAuthKey() {
        String auth = "123@#1234";
        return auth;
    }
}
