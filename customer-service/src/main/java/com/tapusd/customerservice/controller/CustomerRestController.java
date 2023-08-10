package com.tapusd.customerservice.controller;

import com.tapusd.customerservice.adapter.CustomerAdapter;
import com.tapusd.customerservice.domain.Customer;
import com.tapusd.customerservice.dto.request.CreateCustomerDTO;
import com.tapusd.customerservice.dto.response.CustomerDTO;
import com.tapusd.customerservice.exception.NotFoundException;
import com.tapusd.customerservice.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerRestController {

    private final CustomerRepository customerRepository;

    public CustomerRestController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<CustomerDTO> getCustomers() {
        return customerRepository.findAll().stream()
                .map(CustomerAdapter::convertToDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomer(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(CustomerAdapter::convertToDTO)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO saveCustomer(@RequestBody CreateCustomerDTO dto) {

        var customer = new Customer();
        customer.setEmail(dto.email());
        customer.setPassword(dto.password());
        customer.setName(dto.name());
        customer.setDateOfBirth(dto.dateOfBirth());

        Customer savedCustomer = customerRepository.save(customer);
        return CustomerAdapter.convertToDTO(savedCustomer);
    }
}
