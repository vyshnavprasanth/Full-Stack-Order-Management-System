package com.project.ordertracker.Repository;

import com.project.ordertracker.Entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {
    Page<Orders> findByDeliveryStatusIgnoreCase(String deliveryStatus, Pageable pageable);

    Page<Orders> findByDeliveryPricingBetween(Double minPrice, Double maxPrice, Pageable pageable);
    @Query("SELECT MAX(o.deliveryPricing) FROM Orders o")
    Double findMaxDeliveryPricing();

    @Query("SELECT o.orderName FROM Orders o WHERE LOWER(o.orderName) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<String> findByOrderNameContainingIgnoreCase(@Param("query") String query, Pageable pageable);

}

