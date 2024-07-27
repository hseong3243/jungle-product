package com.hseong.jungleproduct.service;

import com.hseong.jungleproduct.domain.Order;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface OrderRepository {
    Long save(Order order);

    Optional<Order> findById(Long orderId);

    Page<Order> findOrdersOrderByCreatedAt(int page, int size);
}
