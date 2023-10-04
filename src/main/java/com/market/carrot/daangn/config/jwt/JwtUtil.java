package com.market.carrot.daangn.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

//    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String createJWT(String username, String secretKey, Long expiredMs) {

        //내가 원하는 정보를 담을 수 있는 공간
        Claims claims = Jwts.claims();
        claims.put("username", username);

        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

    }
}
