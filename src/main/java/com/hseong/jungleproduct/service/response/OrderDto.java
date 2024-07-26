package com.hseong.jungleproduct.service.response;

import com.hseong.jungleproduct.domain.Order;
import java.time.ZonedDateTime;

public record OrderDto(Long orderId, ProductDto product, int amount, ZonedDateTime createdAt) {
    public static OrderDto from(Order order) {
        return new OrderDto(
                order.getOrderId(),
                ProductDto.from(order.getProduct()),
                order.getAmount(),
                order.getCreatedAt()
        );
    }
}
