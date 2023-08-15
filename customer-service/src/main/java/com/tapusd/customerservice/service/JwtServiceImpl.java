package com.tapusd.customerservice.service;

import com.tapusd.customerservice.domain.Customer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {

    private static final String ISSUER = "com.tapusd.microservice";
    private static final Key SECRET_SIGNING_KEY = Keys
            .hmacShaKeyFor("laksdjf27490LKDSHFlk30LSDKJHFllj".getBytes(StandardCharsets.UTF_8));
    private static final JwtParser PARSER = Jwts.parserBuilder()
            .requireIssuer(ISSUER).setSigningKey(SECRET_SIGNING_KEY).build();

    @Override
    public Jws<Claims> verify(String token) {
        return PARSER.parseClaimsJws(token);
    }

    @Override
    public String createToken(Customer customer) {

        Map<String, String> claims = Map.of("roles", "[ROLE_ADMIN, ROLE_USER, ROLE_SEO]");

        return Jwts.builder()
                .setSubject(customer.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 3600))
                .setClaims(claims)
                .setIssuer(ISSUER)
                .signWith(SECRET_SIGNING_KEY)
                .compact();
    }
}
