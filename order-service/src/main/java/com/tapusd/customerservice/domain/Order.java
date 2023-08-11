package com.tapusd.customerservice.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "\"order\"")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private UUID orderNo;

    @NotNull
    private Long customerId;

    @NotBlank
    private String customerName;

    @NotBlank
    @Email
    private String customerEmail;

    @NotNull
    private Double total;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderProduct> orderProducts;

    public Long getId() {
        return id;
    }

    public Order setId(Long id) {
        this.id = id;
        return this;
    }

    public UUID getOrderNo() {
        return orderNo;
    }

    public Order setOrderNo(UUID orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Order setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Order setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public Order setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
        return this;
    }

    public Double getTotal() {
        return total;
    }

    public Order setTotal(Double total) {
        this.total = total;
        return this;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public Order setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
        return this;
    }
}
