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
    public Optional<Product> findByProductNumber(Long productNumber) {
        return products.values().stream().filter(p -> p.getProductNumber().equals(productNumber)).findFirst();
    }

    @Override
    public Long save(Product product) {
        Long nextProductId = getNextProductId();
        products.put(nextProductId, product);
        return nextProductId;
    }

    private Long getNextProductId() {
        return (long) (products.size() + 1);
    }

    @Override
    public List<Product> findAll() {
        return products.values().stream().toList();
    }

    @Override
    public List<Product> search(Long productNumberPrefix) {
        return products.values().stream()
                .filter(product -> product.getProductNumber().toString().startsWith(productNumberPrefix.toString()))
                .toList();
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return Optional.ofNullable(products.get(productId));
    }
}
