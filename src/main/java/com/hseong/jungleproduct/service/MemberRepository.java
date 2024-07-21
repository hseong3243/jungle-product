package com.hseong.jungleproduct.service;

import com.hseong.jungleproduct.domain.Member;
import java.util.Optional;

public interface MemberRepository {

    Optional<Member> findByUsername(String username);
}
