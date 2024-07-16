package com.hseong.jungleproduct.repository;

import com.hseong.jungleproduct.domain.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "product", indexes = @Index(name = "idx_product_number", columnList = "productNumber"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productNumber;
    private String name;
    private int price;
    private int displayAmount = 0;
    private int storageAmount = 0;

    private ProductEntity(Long productId, String productNumber, String name, int price, int displayAmount,
                         int storageAmount) {
        this.productId = productId;
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
        this.displayAmount = displayAmount;
        this.storageAmount = storageAmount;
    }

    public static ProductEntity from(Product product) {
        return new ProductEntity(product.getProductId(), product.getProductNumber().toString(), product.getName(), product.getPrice(),
                product.getDisplayAmount(), product.getStorageAmount());
    }

    public Product toDomain() {
        Product product = new Product(productId, Long.parseLong(productNumber), name, price);
        product.initializeInventory(displayAmount, storageAmount);
        return product;
    }

    public void update(Product product) {
        this.price = product.getPrice();
        this.displayAmount = product.getDisplayAmount();
        this.storageAmount = product.getStorageAmount();
    }
}
