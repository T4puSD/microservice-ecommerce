package com.tapusd.customerservice.service;

import com.tapusd.customerservice.domain.Customer;
import com.tapusd.customerservice.dto.request.CreateCustomerDTO;
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
import java.util.UUID;

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
    public Customer registerNewCustomer(CreateCustomerDTO dto) {
        String passwordSalt = UUID.randomUUID().toString()
                .replace("-", "")
                .substring(0, 12);

        var customer = new Customer();
        customer.setEmail(dto.email());
        customer.setPassword(dto.password());
        customer.setPasswordSalt(passwordSalt);
        customer.setName(dto.name());
        customer.setDateOfBirth(dto.dateOfBirth());
        return customerRepository.save(customer);
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

        if (!isValidPassword(customer, request.password())) {
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

    @Override
    public boolean isValidPassword(Customer customer, String toCheckPassword) {
        return passwordEncoder.matches(
                toCheckPassword + customer.getPasswordSalt(),
                customer.getPassword());
    }
}
