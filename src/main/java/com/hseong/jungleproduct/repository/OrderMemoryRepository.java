package com.hseong.jungleproduct.repository;

import com.hseong.jungleproduct.domain.Order;
import com.hseong.jungleproduct.service.OrderRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

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

    @Override
    public Page<Order> findOrdersOrderByCreatedAt(int page, int size) {
        List<Order> content = orders.values().stream().limit(size).toList();
        return new PageImpl<>(content, PageRequest.of(page, size), orders.size());
    }

    @Override
    public Long calculateSummary(LocalDate calculatedDate) {
        return orders.values().stream()
                .filter(order -> order.getCreatedAt().toLocalDate().isEqual(calculatedDate))
                .mapToLong(order -> (long) order.getProduct().getPrice() * order.getAmount())
                .sum();
    }

    private long getNextId() {
        return orders.size() + 1;
    }
}
