package com.hseong.jungleproduct.repository;

import com.hseong.jungleproduct.service.ProductRepository;
import com.hseong.jungleproduct.domain.Product;
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
}
