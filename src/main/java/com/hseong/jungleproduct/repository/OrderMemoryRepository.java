package com.hseong.jungleproduct.repository;

import com.hseong.jungleproduct.domain.Order;
import com.hseong.jungleproduct.service.OrderRepository;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class OrderMemoryRepository implements OrderRepository {

    private final Map<Long, Order> orders = new ConcurrentHashMap<>();

    @Override
    public Long save(Order order) {
        orders.put(getNextId(), order);
        return (long) orders.size();
    }

    @Override
    public Optional<Order> findById(Long orderId) {
        return Optional.ofNullable(orders.get(orderId));
    }

    private long getNextId() {
        return orders.size() + 1;
    }
}
