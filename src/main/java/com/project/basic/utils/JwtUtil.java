package com.project.basic.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtil {
    // 토큰을 만들기 위한 key
    // 필요시 RefreshToken 또한 따로 추가 한다.
    
    public static String key;
    private static long accessExpire;
    private static long refreshExpire;

    @Value("${jwt.secret.key}")
    public void setKey(String key) {
        JwtUtil.key = key;
    }  
    @Value("${jwt.access.expire}")
    public void setAccessExpire(long accessExpire) {
        JwtUtil.accessExpire = accessExpire;
    }  
    @Value("${jwt.refresh.expire}")
    public void setRefreshExpire(long refreshExpire) {
        JwtUtil.refreshExpire = refreshExpire;
    }  
    
    
    // Token
    public static String create(String email) {
        // JWT INFO
        Map<String, Object> headers = new HashMap<>();
        Map<String, Object> payloads = new HashMap<>();
        // JWT HEADER
        headers.put("type", "jwt");
        
        // JWT에 PAYLOAD:DATA        
        payloads.put("email", email);
        // 만료 기간 설정
        Date expiration = new Date();
        expiration.setTime(expiration.getTime() + accessExpire);

        // JWT 생성
        return Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessExpire))
                .signWith(SignatureAlgorithm.HS256, key.getBytes())
                .compact();
    }

    // Refresh Token
    // Refresh 토큰은 사용자 정보를 가지고 있기에 
    // 위험 함으로 Claims는 따로 넣지 않습니다.
    public static String createRefresh() {
        // JWT INFO
        Map<String, Object> headers = new HashMap<>();
        headers.put("type", "jwt");

        // 만료 기간 설정
        Date expiration = new Date();
        expiration.setTime(expiration.getTime() + refreshExpire);
        
        return Jwts
                .builder()
                .setHeader(headers)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256,  key.getBytes())
                .compact();
    }

    /*
     * 기존 Validate와 GetData를 분리 해서 사용 하였으나
     * 합쳐서 사용을 해보기 위해 작성 하였습니다.
     */
    public static Map<String, Object> getData(String token) {
        Map<String, Object> claimMap = null;
        try {
            claimMap = Jwts.parser()
                            .setSigningKey(key.getBytes("UTF-8"))
                            .parseClaimsJws(token)
                            .getBody();
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        } catch (UnsupportedEncodingException e) {
            log.error("UnsupportedEncodingException");
        }
        return claimMap;
    }
}
