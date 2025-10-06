package com.project.ordertracker.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "order_name")
    private String orderName;

    private LocalDate deliveryDate;

    private Double deliveryPricing;

    private String deliveryStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // No-arg constructor
    public Orders() {
    }

    // All-args constructor
    public Orders(Long orderId, LocalDate deliveryDate, String orderName, Double deliveryPricing, Customer customer, String deliveryStatus) {
        this.orderId = orderId;
        this.deliveryDate = deliveryDate;
        this.orderName = orderName;
        this.deliveryPricing = deliveryPricing;
        this.customer = customer;
        this.deliveryStatus = deliveryStatus;
    }

    // Getters and setters
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Double getDeliveryPricing() {
        return deliveryPricing;
    }

    public void setDeliveryPricing(Double deliveryPricing) {
        this.deliveryPricing = deliveryPricing;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
