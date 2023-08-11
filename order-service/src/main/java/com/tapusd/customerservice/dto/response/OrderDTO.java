package com.tapusd.customerservice.dto.response;

import com.tapusd.customerservice.dto.OrderProductDTO;

import java.util.List;

public record OrderDTO(Long id, String customerName, String customerEmail, Double total, List<OrderProductDTO> orderProducts) {
}
