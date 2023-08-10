package com.tapusd.customerservice.dto.request;

import java.util.List;

public record CreateOrderDTO(List<Long> productIds) {
}
