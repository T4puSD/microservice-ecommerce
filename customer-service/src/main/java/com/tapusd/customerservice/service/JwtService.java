package com.tapusd.customerservice.service;

import com.tapusd.customerservice.domain.Customer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public interface JwtService {
    Jws<Claims> verify(String token);
    String createToken(Customer customer);
}
