package com.hseong.jungleproduct.repository;

import java.time.ZonedDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderDataJpaRepository extends JpaRepository<OrderEntity, Long> {

    @Query(value = "select o from OrderEntity o"
            + " join fetch o.product"
            + " order by o.createdAt desc")
    Page<OrderEntity> findOrdersOrderByCreatedAt(Pageable pageable);

    @Query(value = "select sum(o.amount * p.price) from OrderEntity o"
            + " join o.product p"
            + " where o.createdAt between :startOfDay and :endOfDay")
    Long calculateSummary(@Param("startOfDay") ZonedDateTime startOfDay,
                          @Param("endOfDay") ZonedDateTime endOfDay);
}
