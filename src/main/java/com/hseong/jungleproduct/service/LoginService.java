package com.hseong.jungleproduct.service;

import com.hseong.jungleproduct.domain.Member;
import jakarta.transaction.Transactional;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void login(String username, String password) {
        Member member = memberRepository.findByUsername(username)
            .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));
        member.checkPassword(passwordEncoder, password);
    }
}
