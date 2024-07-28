package com.hseong.jungleproduct.repository;

import com.hseong.jungleproduct.domain.Order;
import com.hseong.jungleproduct.service.OrderRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderJpaRepository implements OrderRepository {

    private final OrderDataJpaRepository orderDataJpaRepository;
    private final ProductDataJpaRepository productDataJpaRepository;

    @Override
    public Long save(Order order) {
        ProductEntity productEntity = productDataJpaRepository.findById(order.getProduct().getProductId())
                .orElseThrow(NoSuchElementException::new);
        OrderEntity orderEntity = orderDataJpaRepository.save(OrderEntity.from(order, productEntity));
        return orderEntity.getOrderId();
    }

    @Override
    public Optional<Order> findById(Long orderId) {
        return orderDataJpaRepository.findById(orderId)
                .map(OrderEntity::toDomain);
    }

    @Override
    public Page<Order> findOrdersOrderByCreatedAt(int page, int size) {
        return orderDataJpaRepository.findOrdersOrderByCreatedAt(PageRequest.of(page, size))
                .map(OrderEntity::toDomain);
    }

    @Override
    public Long calculateSummary(LocalDate calculatedDate) {
        ZonedDateTime startOfDay = ZonedDateTime.of(calculatedDate, LocalTime.MIN, ZoneId.of("Asia/Seoul"));
        ZonedDateTime endOfDay = ZonedDateTime.of(calculatedDate, LocalTime.MAX, ZoneId.of("Asia/Seoul"));
        return orderDataJpaRepository.calculateSummary(startOfDay, endOfDay);
    }
}
