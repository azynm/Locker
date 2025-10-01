package com.example.authproj.security;


import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.authproj.config.JwtProperties;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
    private final JwtProperties jwtProperties;

    public JWTService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String getSecretKey() {
        return jwtProperties.getSecret();
    }
    public long getExpiration() {
        return jwtProperties.getExpiration();
    }

    public String generateToken(String email) {
        String subject = email;
        String secretKey = jwtProperties.getSecret();
        Date whenIssued = new Date();
        Date whenExpires = new Date(whenIssued.getTime() + jwtProperties.getExpiration());
        long expirationTime = jwtProperties.getExpiration();

        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(whenIssued)
                .setExpiration(whenExpires)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

    public String getKey() {
        return jwtProperties.getSecret();  
    }
    public long getExpTime() {
        return jwtProperties.getExpiration();  
    }

}
