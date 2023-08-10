package com.tapusd.customerservice.adapter;

import com.tapusd.customerservice.domain.Order;
import com.tapusd.customerservice.dto.response.ProductDTO;

public class ProductAdapter {

    private ProductAdapter() {

    }

    public static ProductDTO convertToDTO(Order order) {
//        return new ProductDTO(order.getId(),
//                order.getName());
        return new ProductDTO(1000L, "any");
    }
}
