package com.hseong.jungleproduct.service;

import com.hseong.jungleproduct.auth.JwtProvider;
import com.hseong.jungleproduct.auth.TokenResponse;
import com.hseong.jungleproduct.domain.Member;
import com.hseong.jungleproduct.service.response.LoginResponse;
import jakarta.transaction.Transactional;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public LoginResponse login(String username, String password) {
        Member member = memberRepository.findByUsername(username)
            .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));
        member.checkPassword(passwordEncoder, password);

        TokenResponse token = jwtProvider.createToken(member.getMemberId(), member.getMemberRole());
        return LoginResponse.of(member, token);
    }
}
