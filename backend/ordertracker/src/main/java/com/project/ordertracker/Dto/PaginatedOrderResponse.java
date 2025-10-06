package com.project.ordertracker.Dto;

import java.util.List;

public class PaginatedOrderResponse {
    private List<OrderWithCustomerDTO> orders;
    private int totalPages;
    private long totalElements;

    public PaginatedOrderResponse(int totalPages, long totalElements, List<OrderWithCustomerDTO> orders) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.orders = orders;
    }

    public List<OrderWithCustomerDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderWithCustomerDTO> orders) {
        this.orders = orders;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public PaginatedOrderResponse() {
    }
}
