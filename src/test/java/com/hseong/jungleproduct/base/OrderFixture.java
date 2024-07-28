package com.hseong.jungleproduct.base;

import com.hseong.jungleproduct.domain.Order;
import com.hseong.jungleproduct.domain.Product;
import com.hseong.jungleproduct.repository.OrderEntity;
import com.hseong.jungleproduct.repository.ProductEntity;
import java.time.ZonedDateTime;

public class OrderFixture {
    public static OrderEntity orderEntity(Product product, int amount, ZonedDateTime createdAt) {
        Order order = new Order(null, product, amount, createdAt);
        return OrderEntity.from(order, ProductEntity.from(product));
    }
}
