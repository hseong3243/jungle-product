package com.hseong.jungleproduct.service;

import com.hseong.jungleproduct.domain.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(Long productId);

    Long save(Product product);

    List<Product> findAll();

    List<Product> search(Long productIdPrefix);
}
