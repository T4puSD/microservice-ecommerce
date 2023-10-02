package com.tapusd.customerservice.service;

import com.tapusd.customerservice.domain.Customer;
import com.tapusd.customerservice.dto.request.CreateCustomerDTO;
import com.tapusd.customerservice.dto.request.LoginRequest;
import com.tapusd.customerservice.dto.response.LoginResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer registerNewCustomer(CreateCustomerDTO dto);

    Customer save(Customer customer);

    List<Customer> findAll();

    Optional<Customer> findById(Long id);

    Optional<Customer> findByEmail(String email);

    LoginResponse login(LoginRequest request);

    Jws<Claims> verifyLogin(String jwtToken);

    boolean isValidToken(String jwtToken);

    boolean isValidPassword(Customer customer, String toCheckPassword);
}
