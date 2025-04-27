package com.example.demo.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "my-super-secret-key-32-characters-1001010010101010101";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    //黑名单检查
        /*在实际应用中，JWT 是无状态的，一旦生成就无法直接销毁。一种常见的做法是使用一个黑名单机制，
        将需要销毁的 token 加入黑名单，在验证 token 时，除了验证签名和过期时间，还需要检查 token 是否在黑名单中。
        以下是一个简单的示例，假设我们有一个 TokenBlacklistService 来管理黑名单*/
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }
}    