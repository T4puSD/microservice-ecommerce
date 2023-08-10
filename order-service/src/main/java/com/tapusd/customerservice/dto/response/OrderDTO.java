package com.tapusd.customerservice.dto.response;

import java.util.List;

public record OrderDTO(long id, double price, List<ProductDTO> products) {
}
