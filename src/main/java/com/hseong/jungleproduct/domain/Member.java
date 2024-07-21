package com.hseong.jungleproduct.domain;

import com.hseong.jungleproduct.service.PasswordEncoder;
import java.util.NoSuchElementException;
import lombok.Getter;

@Getter
public class Member {

    private final Long memberId;
    private final String username;
    private final String password;
    private final MemberRole memberRole;

    public Member(String username, String password, MemberRole memberRole) {
        this(null, username, password, memberRole);
    }

    public Member(Long memberId, String username, String password, MemberRole memberRole) {
        this.memberId = memberId;
        this.username = username;
        this.password = password;
        this.memberRole = memberRole;
    }

    public void checkPassword(PasswordEncoder passwordEncoder, String rawPassword) {
        if(passwordEncoder.matches(rawPassword, password)) {
            return;
        }
        throw new NoSuchElementException("존재하지 않는 회원입니다.");
    }
}
