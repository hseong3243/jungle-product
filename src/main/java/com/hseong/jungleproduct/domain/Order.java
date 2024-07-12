package com.hseong.jungleproduct.domain;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Order {
    private static final String ASIA_SEOUL = "Asia/Seoul";

    private final Long orderId;
    private final Product product;
    private final int amount;
    private final ZonedDateTime createdAt = ZonedDateTime.now(ZoneId.of(ASIA_SEOUL));

    public Order(Product product, int amount) {
        this(null, product, amount);
    }

    public Order(Long orderId, Product product, int amount) {
        this.orderId = orderId;
        this.product = product;
        this.amount = amount;
    }
}
