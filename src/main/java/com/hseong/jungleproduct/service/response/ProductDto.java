package com.hseong.jungleproduct.service.response;

import com.hseong.jungleproduct.domain.Product;

public record ProductDto(Long productId, String name, int price, int displayAmount, int storageAmount) {
    public static ProductDto from(Product product) {
        return new ProductDto(product.getProductNumber(), product.getName(), product.getPrice(), product.getDisplayAmount(),
                product.getStorageAmount());
    }
}
