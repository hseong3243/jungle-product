package com.hseong.jungleproduct.domain;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.Getter;

@Getter
public class Order {
    private static final String ASIA_SEOUL = "Asia/Seoul";

    private final Long orderId;
    private final Product product;
    private final int amount;
    private final ZonedDateTime createdAt;

    public Order(Long orderId, Product product, int amount, ZonedDateTime createdAt) {
        this.orderId = orderId;
        this.product = product;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public static Order buy(Product product, int amount) {
        product.buy(amount);
        return new Order(null, product, amount, ZonedDateTime.now(ZoneId.of(ASIA_SEOUL)));
    }
}
