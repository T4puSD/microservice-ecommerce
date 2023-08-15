package com.tapusd.customerservice.controller;

import com.tapusd.customerservice.adapter.CustomerAdapter;
import com.tapusd.customerservice.domain.Customer;
import com.tapusd.customerservice.dto.request.CreateCustomerDTO;
import com.tapusd.customerservice.dto.request.LoginRequest;
import com.tapusd.customerservice.dto.response.CustomerDTO;
import com.tapusd.customerservice.dto.response.LoginResponse;
import com.tapusd.customerservice.exception.AuthenticationException;
import com.tapusd.customerservice.service.CustomerService;
import com.tapusd.customerservice.service.CustomerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    private final CustomerService customerService;

    public AuthController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ex.getMessage());
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO saveCustomer(@RequestBody CreateCustomerDTO dto) {

        var customer = new Customer();
        customer.setEmail(dto.email());
        customer.setPassword(dto.password());
        customer.setName(dto.name());
        customer.setDateOfBirth(dto.dateOfBirth());

        Customer savedCustomer = customerService.save(customer);
        return CustomerAdapter.convertToDTO(savedCustomer);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return customerService.login(request);
    }

    @GetMapping("/validate")
    public boolean isValidToken(@RequestParam String token) {
        return  customerService.isValidToken(token);
    }
}
