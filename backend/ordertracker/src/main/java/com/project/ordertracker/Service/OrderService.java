package com.project.ordertracker.Service;

import com.project.ordertracker.Dto.OrderWithCustomerDTO;
import com.project.ordertracker.Dto.PaginatedOrderResponse;
import com.project.ordertracker.Entity.Orders;
import com.project.ordertracker.Repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    OrdersRepository ordersRepository;

    public PaginatedOrderResponse getAllOrders(String status,int page, int  size) {
        Page<Orders> orders;
//        Double maxPrice = maxPrize!=-1 ? maxPrize : ordersRepository.findMaxDeliveryPricing();
//        Pageable pageable = PageRequest.of(page, size, Sort.by("deliveryPricing").ascending());
        Pageable pageable = PageRequest.of(page, size, Sort.by("OrderId").descending());
        if ("all".equalsIgnoreCase(status)) {
            orders = ordersRepository.findAll(pageable);
//              orders = ordersRepository.findByDeliveryPricingBetween(0.0, maxPrice, pageable);
        }
        else {
              orders = ordersRepository.findByDeliveryStatusIgnoreCase(status, pageable);

        }
        List<OrderWithCustomerDTO> orderDTOs = orders.getContent().stream()
                .map(order -> new OrderWithCustomerDTO(
                        order.getOrderId(),
                        order.getOrderName(),
                        order.getDeliveryDate(),
                        order.getDeliveryPricing(),
                        order.getDeliveryStatus(),
                        order.getCustomer() != null ? order.getCustomer().getName() : null
                ))
                .collect(Collectors.toList());
       return  new PaginatedOrderResponse(orders.getTotalPages(),orders.getTotalElements(), orderDTOs);

    }

    public Page<String> getautosuggest(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ordersRepository.findByOrderNameContainingIgnoreCase(name,pageable);
    }
}
