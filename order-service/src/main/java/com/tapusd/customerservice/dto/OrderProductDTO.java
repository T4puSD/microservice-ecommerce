package com.tapusd.customerservice.dto;

public record OrderProductDTO(Long id, Long productId, Double price, Integer quantity) {
}
