package com.tapusd.customerservice.dto;

import java.time.LocalDate;

public record CustomerDTO(Long id, String name, String email, LocalDate dateOfBirth) {
}

