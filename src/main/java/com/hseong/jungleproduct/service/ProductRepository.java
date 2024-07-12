package com.hseong.jungleproduct.service;

import com.hseong.jungleproduct.domain.Product;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(Long productId);

    Long save(Product product);
}
