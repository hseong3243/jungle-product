package com.hseong.jungleproduct.repository;

import com.hseong.jungleproduct.domain.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;
    private int amount;
    private ZonedDateTime createdAt;

    private OrderEntity(ProductEntity product, int amount, ZonedDateTime createdAt) {
        this.product = product;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public static OrderEntity from(Order order, ProductEntity product) {
        return new OrderEntity(product, order.getAmount(), ZonedDateTime.now());
    }

    public Order toDomain() {
        return new Order(orderId, product.toDomain(), amount);
    }
}
