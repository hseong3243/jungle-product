package com.hseong.jungleproduct.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    private Long productId;
    private String name;
    private int price;
    private int displayAmount = 0;
    private int storageAmount = 0;

    public Product(Long productId, String name, int price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public void buy(int amount) {
        if(displayAmount < amount) {
            throw new IllegalArgumentException("매대의 재고 수량이 충분하지 않습니다.");
        }
        displayAmount -= amount;
    }
}
