package com.hseong.jungleproduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDataJpaRepository extends JpaRepository<OrderEntity, Long> {
}
