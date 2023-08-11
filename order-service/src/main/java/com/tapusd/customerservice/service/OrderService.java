package com.tapusd.customerservice.service;

import com.tapusd.customerservice.domain.Order;
import com.tapusd.customerservice.dto.request.CreateOrderDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order createOrder(CreateOrderDTO dto);

    List<Order> findAll();

    Optional<Order> findById(Long id);
}
