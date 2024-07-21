package com.hseong.jungleproduct.domain;

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
}
