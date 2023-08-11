package com.tapusd.customerservice.controller;

import com.tapusd.customerservice.adapter.OrderAdapter;
import com.tapusd.customerservice.domain.Order;
import com.tapusd.customerservice.dto.request.CreateOrderDTO;
import com.tapusd.customerservice.dto.response.OrderDTO;
import com.tapusd.customerservice.exception.NotFoundException;
import com.tapusd.customerservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDTO> getOrders() {
        return orderService.findAll().stream()
                .map(OrderAdapter::convertToDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public OrderDTO getOrder(@PathVariable Long id) {
        return orderService.findById(id)
                .map(OrderAdapter::convertToDTO)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO saveOrder(@RequestBody CreateOrderDTO dto) {
        Order savedOrder = orderService.createOrder(dto);
        return OrderAdapter.convertToDTO(savedOrder);
    }
}
