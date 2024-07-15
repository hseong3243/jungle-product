package com.hseong.jungleproduct.repository;

import com.hseong.jungleproduct.domain.Product;
import com.hseong.jungleproduct.service.ProductRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ProductMemoryRepository implements ProductRepository {

    private final Map<Long, Product> products = new ConcurrentHashMap<>();

    @Override
    public Optional<Product> findById(Long productId) {
        return Optional.ofNullable(products.get(productId));
    }

    @Override
    public Long save(Product product) {
        products.put(product.getProductId(), product);
        return product.getProductId();
    }

    @Override
    public List<Product> findAll() {
        return products.values().stream().toList();
    }

    @Override
    public List<Product> search(Long productIdPrefix) {
        return products.values().stream()
                .filter(product -> product.getProductId().toString().startsWith(productIdPrefix.toString()))
                .toList();
    }
}
