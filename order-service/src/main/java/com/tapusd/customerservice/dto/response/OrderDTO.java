package com.tapusd.customerservice.dto.response;

import com.tapusd.customerservice.dto.OrderProductDTO;

import java.util.List;
import java.util.UUID;

public record OrderDTO(Long id,
                       UUID orderNo,
                       String customerName,
                       String customerEmail,
                       Double total,
                       List<OrderProductDTO> orderProducts) {
}
