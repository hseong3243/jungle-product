package com.hseong.jungleproduct.service;

import com.hseong.jungleproduct.domain.Product;
import jakarta.transaction.Transactional;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Long addProduct(Long productId, String name, int price) {
        Product product = new Product(productId, name, price);
        return productRepository.save(product);
    }

    public void initializeAmount(Long productId, int displayAmount, int storageAmount) {
        Product product = productRepository.findById(productId)
                .orElseThrow(NoSuchElementException::new);
        product.initializeInventory(displayAmount, storageAmount);
    }
}
