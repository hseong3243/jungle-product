package com.hseong.jungleproduct.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    private Long productId;
    private Long productNumber;
    private String name;
    private int price;
    private int displayAmount = 0;
    private int storageAmount = 0;

    public Product(Long productNumber, String name, int price) {
        this(null, productNumber, name, price);
    }

    public Product(Long productId, Long productNumber, String name, int price) {
        this.productId = productId;
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
    }

    public void buy(int amount) {
        displayAmount -= amount;
    }

    public void initializeInventory(int displayAmount, int storageAmount) {
        validate(displayAmount);
        validate(storageAmount);
        this.displayAmount = displayAmount;
        this.storageAmount = storageAmount;
    }

    private void validate(int amount) {
        if(amount < 0) {
            throw new IllegalArgumentException("재고를 음수로 초기화할 수 없습니다.");
        }
    }
}
