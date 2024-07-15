package com.hseong.jungleproduct.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDataJpaRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAllByProductNumberStartsWith(String productNumber);

    Optional<ProductEntity> findByProductNumber(String productNumber);
}
