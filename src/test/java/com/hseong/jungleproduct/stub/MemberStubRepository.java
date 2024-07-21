package com.hseong.jungleproduct.stub;

import com.hseong.jungleproduct.domain.Member;
import com.hseong.jungleproduct.service.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberStubRepository implements MemberRepository {

    public final List<Member> members = new ArrayList<>();

    public void stub(Member member) {
        members.add(member);
    }

    @Override
    public Optional<Member> findByUsername(String username) {
        return members.stream().findFirst();
    }
}
