package com.hseong.jungleproduct.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderDataJpaRepository extends JpaRepository<OrderEntity, Long> {

    @Query(value = "select o from OrderEntity o"
            + " join fetch o.product"
            + " order by o.createdAt desc")
    Page<OrderEntity> findOrdersOrderByCreatedAt(Pageable pageable);
}
