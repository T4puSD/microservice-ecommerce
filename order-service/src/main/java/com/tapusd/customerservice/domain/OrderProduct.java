package com.tapusd.customerservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_product")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Long productId;

    private Double price;

    private Integer quantity;

    public Long getId() {
        return id;
    }

    public OrderProduct setId(Long id) {
        this.id = id;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public OrderProduct setOrder(Order order) {
        this.order = order;
        return this;
    }

    public Long getProductId() {
        return productId;
    }

    public OrderProduct setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public OrderProduct setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderProduct setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}
