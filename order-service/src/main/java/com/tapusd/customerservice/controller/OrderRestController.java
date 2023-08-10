package com.tapusd.customerservice.controller;

import com.tapusd.customerservice.adapter.ProductAdapter;
import com.tapusd.customerservice.domain.Order;
import com.tapusd.customerservice.dto.request.CreateOrderDTO;
import com.tapusd.customerservice.dto.response.ProductDTO;
import com.tapusd.customerservice.exception.NotFoundException;
import com.tapusd.customerservice.repository.OrderRepository;
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

    private final OrderRepository orderRepository;

    public OrderRestController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<ProductDTO> getCustomers() {
        return orderRepository.findAll().stream()
                .map(ProductAdapter::convertToDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ProductDTO getCustomer(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(ProductAdapter::convertToDTO)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO saveCustomer(@RequestBody CreateOrderDTO dto) {

        var order = new Order();
//        order.setName(dto.name());

        Order savedOrder = orderRepository.save(order);
        return ProductAdapter.convertToDTO(savedOrder);
    }
}
