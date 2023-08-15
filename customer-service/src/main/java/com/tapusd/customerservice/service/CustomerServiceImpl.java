package com.tapusd.customerservice.service;

import com.tapusd.customerservice.domain.Customer;
import com.tapusd.customerservice.dto.request.LoginRequest;
import com.tapusd.customerservice.dto.response.LoginResponse;
import com.tapusd.customerservice.exception.AuthenticationException;
import com.tapusd.customerservice.repository.CustomerRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               JwtService jwtService,
                               PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        if (Objects.isNull(request)) {
            throw new AuthenticationException("Invalid request body can not be null");
        }

        Customer customer = customerRepository.findByEmail(request.email())
                .orElseThrow(() -> new AuthenticationException("Email not found!"));

        if (!passwordEncoder.matches(request.password(), customer.getPassword())) {
            throw new AuthenticationException("Invalid credentials!");
        }

        String token = jwtService.createToken(customer);
        return new LoginResponse(token);
    }

    @Override
    public Jws<Claims> verifyLogin(String jwtToken) {
        return jwtService.verify(jwtToken);
    }

    @Override
    public boolean isValidToken(String jwtToken) {
        try {
            Jws<Claims> claimsJws = jwtService.verify(jwtToken);
            if (Objects.isNull(claimsJws)) {
                throw new AuthenticationException("Invalid credentials!");
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
