package com.project.ordertracker.Controller;

import com.project.ordertracker.Dto.OrderWithCustomerDTO;
import com.project.ordertracker.Dto.PaginatedOrderResponse;
import com.project.ordertracker.Service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
public class Orders {

    @Autowired
    OrderService orderService;

    @GetMapping("/api/orders")
    public PaginatedOrderResponse getOrders(@RequestParam String status,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size
                                                ) {
        return orderService.getAllOrders(status,page, size);
    }

    @GetMapping("/api/orders/autosuggest")
    public Page autosuggest(@RequestParam String name,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size
    ) {
        return orderService.getautosuggest(name,page, size);
    }

}