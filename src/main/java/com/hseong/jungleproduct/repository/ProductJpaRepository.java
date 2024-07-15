package com.hseong.jungleproduct.repository;

import com.hseong.jungleproduct.domain.Product;
import com.hseong.jungleproduct.service.ProductRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductJpaRepository implements ProductRepository {

    private final ProductDataJpaRepository productDataJpaRepository;

    @Override
    public Optional<Product> findById(Long productId) {
        return productDataJpaRepository.findByProductNumber(String.valueOf(productId))
                .map(ProductEntity::toDomain);
    }

    @Override
    public Long save(Product product) {
        ProductEntity entity = productDataJpaRepository.save(ProductEntity.from(product));
        return entity.getProductId();
    }

    @Override
    public List<Product> findAll() {
        return productDataJpaRepository.findAll().stream()
                .map(ProductEntity::toDomain)
                .toList();
    }

    @Override
    public List<Product> search(Long productIdPrefix) {
        return productDataJpaRepository.findAllByProductNumberStartsWith(productIdPrefix.toString())
                .stream()
                .map(ProductEntity::toDomain)
                .toList();
    }
}
