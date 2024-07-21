package com.hseong.jungleproduct.repository;

import com.hseong.jungleproduct.domain.Member;
import com.hseong.jungleproduct.service.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository implements MemberRepository {

    private final MemberDataJpaRepository repository;

    @Override
    public Optional<Member> findByUsername(String username) {
        return repository.findByUsername(username)
            .map(MemberEntity::toDomain);
    }
}
