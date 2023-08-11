package com.tapusd.customerservice.dto.request;

import java.util.List;

public record CreateOrderDTO(Long customerId, List<OrderProductCreateDTO> orderProductCreateDTOList) {
}
