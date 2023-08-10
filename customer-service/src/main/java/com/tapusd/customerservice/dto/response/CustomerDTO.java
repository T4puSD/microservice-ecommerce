package com.tapusd.customerservice.dto.response;

import java.time.LocalDate;

public record CustomerDTO(Long id, String name, String email, LocalDate dateOfBirth) {
}
