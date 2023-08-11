package com.tapusd.customerservice.service;

import com.tapusd.customerservice.domain.Order;
import com.tapusd.customerservice.domain.OrderProduct;
import com.tapusd.customerservice.dto.request.CreateOrderDTO;
import com.tapusd.customerservice.exception.NotFoundException;
import com.tapusd.customerservice.feingclients.CustomerFeignClient;
import com.tapusd.customerservice.feingclients.ProductFeignClient;
import com.tapusd.customerservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerFeignClient customerFeignClient;
    private final ProductFeignClient productFeignClient;

    public OrderServiceImpl(OrderRepository orderRepository,
                            CustomerFeignClient customerFeignClient,
                            ProductFeignClient productFeignClient) {
        this.orderRepository = orderRepository;
        this.customerFeignClient = customerFeignClient;
        this.productFeignClient = productFeignClient;
    }

    @Override
    @Transactional
    public Order createOrder(CreateOrderDTO dto) {

        var customer = customerFeignClient.getCustomer(dto.customerId());

        if (Objects.isNull(customer)) {
            throw new NotFoundException("Customer not found with provided id");
        }

        var order = new Order()
                .setCustomerId(dto.customerId())
                .setCustomerName(customer.name())
                .setCustomerEmail(customer.email())
                .setOrderNo(UUID.randomUUID());


        List<OrderProduct> orderProducts = getOrderProducts(dto, order);
        order.setTotal(getOrderTotal(orderProducts));
        order.setOrderProducts(orderProducts);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    private Double getOrderTotal(List<OrderProduct> orderProducts) {
        return orderProducts.parallelStream()
                .mapToDouble(orderProduct -> orderProduct.getPrice() * orderProduct.getQuantity())
                .sum();
    }

    private List<OrderProduct> getOrderProducts(CreateOrderDTO dto, Order order) {
        return dto.orderProductCreateDTOList().stream()
                .map(orderProductCreateDTO -> {
                    var productDTO = productFeignClient.getProduct(orderProductCreateDTO.productId());

                    if (Objects.isNull(productDTO)) {
                        throw new NotFoundException("Product not found with provided id!");
                    }

                    return new OrderProduct()
                            .setOrder(order)
                            .setProductId(productDTO.id())
                            .setPrice(productDTO.price())
                            .setQuantity(orderProductCreateDTO.quantity());
                }).toList();
    }
}
