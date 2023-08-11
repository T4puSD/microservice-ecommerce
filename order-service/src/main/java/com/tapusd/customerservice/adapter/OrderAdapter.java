package com.tapusd.customerservice.adapter;

import com.tapusd.customerservice.domain.Order;
import com.tapusd.customerservice.domain.OrderProduct;
import com.tapusd.customerservice.dto.OrderProductDTO;
import com.tapusd.customerservice.dto.response.OrderDTO;

import java.util.List;

public class OrderAdapter {

    private OrderAdapter() {

    }

    public static OrderDTO convertToDTO(Order order) {
        return new OrderDTO(order.getId(), order.getCustomerName(), order.getCustomerEmail(), order.getTotal(), convertToDTOList(order.getOrderProducts()));
    }

    private static List<OrderProductDTO> convertToDTOList(List<OrderProduct> orderProducts) {
        return orderProducts.stream()
                .map(OrderAdapter::convertToDTO)
                .toList();
    }

    public static OrderProductDTO convertToDTO(OrderProduct orderProduct) {
        return new OrderProductDTO(orderProduct.getId(), orderProduct.getProductId(), orderProduct.getPrice(), orderProduct.getQuantity());
    }
}
