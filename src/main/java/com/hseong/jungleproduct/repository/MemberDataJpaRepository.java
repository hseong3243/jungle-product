package com.hseong.jungleproduct.repository;

import com.hseong.jungleproduct.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDataJpaRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByUsername(String username);
}
