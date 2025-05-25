package com.bb.blog.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${spring.application.jwt.expiration}")
    private long expiration;
    @Value("${spring.application.jwt.secret}")
    private String secret;
    @Value("${spring.application.jwt.refresh-threshold}") // 新增：刷新阈值 (秒)
    private long refreshThreshold;

    private SecretKey key;
    private static final SecureDigestAlgorithm<SecretKey, SecretKey> ALGORITHM = Jwts.SIG.HS256;


    @PostConstruct // 在属性注入后初始化密钥
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }


    public String generateToken(String email) {
        // 可以保留 UUID 作为 JTI (JWT ID)
        String uuid = UUID.randomUUID().toString();
        Date issuedAt = new Date();
        Date expireDate = Date.from(Instant.now().plusSeconds(expiration));

        return Jwts.builder()
                .header()
                .add("typ", "JWT")
                .and()
                .id(uuid) // JWT ID
                .subject(email) // 用户主题，这里取邮箱
                .issuedAt(issuedAt)
                .expiration(expireDate)
                .signWith(key, ALGORITHM)
                .compact();
    }
    // 从 Token 中提取所有声明
    public Claims extractAllClaims(String token) {
        try {
            return Jwts
                    .parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            // Token 已过期，仍然返回 claims 以便检查其他信息 (如果需要)
            return e.getClaims();
        } catch (JwtException e) {
            // 其他 JWT 解析或签名错误
            throw new RuntimeException("Invalid Token: " + e.getMessage(), e);
        }
    }

    // 从 Token 中提取特定声明
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // 从 Token 中提取用户主题 (subject)
    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 从 Token 中提取过期时间
    public Date extractExpiration(String token) {
        try {
            return extractClaim(token, Claims::getExpiration);
        } catch (ExpiredJwtException e) {
            return e.getClaims().getExpiration(); // If expired, get expiration from expired claims
        }
    }

    // 校验 Token 是否有效 (未过期且签名正确)
    public boolean isTokenValid(String token) {
        try {
            // This parses and validates signature and expiration in one step
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true; // If no exception, it's valid
        } catch (JwtException e) {
            // Catches ExpiredJwtException, SignatureException, MalformedJwtException, etc.
            return false;
        }
    }

    // 检查 Token 是否过期
    public boolean isTokenExpired(String token) {
        try {
            // Attempt to extract expiration, which will throw ExpiredJwtException if it's past
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getExpiration();
            return false; // If we reach here, it's not expired
        } catch (ExpiredJwtException e) {
            return true; // ExpiredJwtException means it's expired
        } catch (JwtException e) {
            // Other JWT errors (invalid signature, malformed) - treat as invalid/expired for safety
            return true;
        }
    }


    // 检查 Token 是否接近过期 (基于 refreshThreshold)
    public boolean isTokenAboutToExpire(String token) {
        Date expirationDate = extractExpiration(token);
        if (expirationDate == null) {
            return false; // Cannot determine expiration
        }
        long timeUntilExpirationSeconds = (expirationDate.getTime() - System.currentTimeMillis()) / 1000;
        return timeUntilExpirationSeconds > 0 && timeUntilExpirationSeconds < refreshThreshold;
    }

    // 获取请求头中的 Token 字符串
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // 移除 "Bearer " 前缀
        }
        return null;
    }
}
