package com.hseong.jungleproduct.service;

import com.hseong.jungleproduct.domain.Product;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Long addProduct(Long productId, String name, int price) {
        Product product = new Product(productId, name, price);
        return productRepository.save(product);
    }

    public void initializeAmount(Long productId, int displayAmount, int storageAmount) {
        Product product = getProduct(productId);
        product.initializeInventory(displayAmount, storageAmount);
    }

    public ProductDto findProduct(Long productId) {
        Product product = getProduct(productId);
        return ProductDto.from(product);
    }

    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductDto::from)
                .toList();
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(NoSuchElementException::new);
    }

    public List<ProductDto> searchProduct(Long productIdPrefix) {
        List<Product> products = productRepository.search(productIdPrefix);
        return products.stream()
                .map(ProductDto::from)
                .toList();
    }
}
