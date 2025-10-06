package com.project.ordertracker.Dto;

import java.time.LocalDate;

public class OrderWithCustomerDTO {

    private Long orderId;
    private String orderName;
    private LocalDate deliveryDate;
    private Double deliveryPricing;
    private String deliveryStatus;
    private String customerName;  // <- from associated Customer
    private String totalPage;

    public OrderWithCustomerDTO(Long orderId, String orderName, LocalDate deliveryDate, Double deliveryPricing, String deliveryStatus, String customerName) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.deliveryDate = deliveryDate;
        this.deliveryPricing = deliveryPricing;
        this.deliveryStatus = deliveryStatus;
        this.customerName = customerName;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    // Constructors
    public OrderWithCustomerDTO() {
    }

    public OrderWithCustomerDTO(Long orderId, String orderName, LocalDate deliveryDate, Double deliveryPricing, String deliveryStatus, String customerName, String totalPage) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.deliveryDate = deliveryDate;
        this.deliveryPricing = deliveryPricing;
        this.deliveryStatus = deliveryStatus;
        this.customerName = customerName;
        this.totalPage = totalPage;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
