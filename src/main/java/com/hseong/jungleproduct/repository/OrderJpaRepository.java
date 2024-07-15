package com.hseong.jungleproduct.repository;

import com.hseong.jungleproduct.domain.Order;
import com.hseong.jungleproduct.service.OrderRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
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
}
