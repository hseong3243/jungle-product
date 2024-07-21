package com.hseong.jungleproduct.repository;

import com.hseong.jungleproduct.domain.Member;
import com.hseong.jungleproduct.domain.MemberRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    private MemberEntity(String username, String password) {
        this(null, username, password);
    }

    private MemberEntity(Long memberId, String username, String password) {
        this.memberId = memberId;
        this.username = username;
        this.password = password;
    }

    public static MemberEntity from(Member member) {
        return new MemberEntity(member.getUsername(), member.getPassword());
    }

    public Member toDomain() {
        return new Member(memberId, username, password, memberRole);
    }
}
