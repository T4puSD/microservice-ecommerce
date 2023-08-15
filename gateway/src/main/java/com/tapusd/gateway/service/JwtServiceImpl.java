package com.tapusd.gateway.service;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Service
public class JwtServiceImpl implements JwtService {

    private static final String ISSUER = "com.tapusd.microservice";
    private static final Key SECRET_SIGNING_KEY = Keys
            .hmacShaKeyFor("laksdjf27490LKDSHFlk30LSDKJHFllj".getBytes(StandardCharsets.UTF_8));
    private static final JwtParser PARSER = Jwts.parserBuilder()
            .requireIssuer(ISSUER).setSigningKey(SECRET_SIGNING_KEY).build();

    @Override
    public boolean isValidToken(String token) {
        try {
            PARSER.parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
