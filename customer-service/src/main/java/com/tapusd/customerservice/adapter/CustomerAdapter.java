package com.tapusd.customerservice.adapter;

import com.tapusd.customerservice.domain.Customer;
import com.tapusd.customerservice.dto.response.CustomerDTO;

public class CustomerAdapter {

    private CustomerAdapter () {

    }

    public static CustomerDTO convertToDTO(Customer customer) {
        return new CustomerDTO(customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getDateOfBirth());
    }
}
