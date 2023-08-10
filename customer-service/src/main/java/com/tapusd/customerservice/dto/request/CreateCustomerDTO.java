package com.tapusd.customerservice.dto.request;

import java.time.LocalDate;

public record CreateCustomerDTO(String name, String email, String password, LocalDate dateOfBirth) {
}
