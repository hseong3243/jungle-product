package com.hseong.jungleproduct.service;

import com.hseong.jungleproduct.domain.Order;
import java.util.Optional;

public interface OrderRepository {
    Long save(Order order);

    Optional<Order> findById(Long orderId);
}
