package com.hseong.jungleproduct.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;
import static org.junit.jupiter.api.Assertions.*;

import com.hseong.jungleproduct.domain.Member;
import com.hseong.jungleproduct.domain.MemberRole;
import com.hseong.jungleproduct.stub.MemberStubRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LoginServiceTest {

    private LoginService loginService;
    private MemberStubRepository memberRepository;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        memberRepository = new MemberStubRepository();
        passwordEncoder = new PasswordEncoder() {
            @Override
            public String encode(String rawPassword) {
                return new StringBuilder(rawPassword).reverse().toString();
            }

            @Override
            public boolean matches(String rawPassword, String encodedPassword) {
                return new StringBuilder(encodedPassword).reverse().toString().equals(rawPassword);
            }
        };
        loginService = new LoginService(memberRepository, passwordEncoder);
    }

    @Nested
    @DisplayName("login 호출 시")
    class LoginTest {

        @Test
        @DisplayName("예외(NoSuchElement): 아이디가 일치하지 않으면")
        void whenNoMatchUsername() {
            //given
            String username = "username";
            String password = passwordEncoder.encode("password");
            Member member = new Member(username, password, MemberRole.ADMIN);
            String wrongUsername = "wrongUsername";

            memberRepository.stub(member);

            //when
            Exception exception = catchException(() -> loginService.login(wrongUsername, password));

            //then
            assertThat(exception).isInstanceOf(NoSuchElementException.class);
        }

        @Test
        @DisplayName("예외(NoSuchElement): 패스워드가 일치하지 않으면")
        void whenNoMatchPassword() {
            //given
            String username = "username";
            String password = passwordEncoder.encode("password");
            Member member = new Member(username, password, MemberRole.ADMIN);
            String wrongPassword = "wrongPassword";

            memberRepository.stub(member);

            //when
            Exception exception = catchException(() -> loginService.login(username, wrongPassword));

            //then
            assertThat(exception).isInstanceOf(NoSuchElementException.class);
        }

        @Test
        @DisplayName("아이디, 패스워드가 일치하면 회원 ID를 반환한다.")
        void login() {
            //given
            String username = "username";
            String rawPassword = "password";
            String encodedPassword = passwordEncoder.encode(rawPassword);
            Member member = new Member(1L, username, encodedPassword, MemberRole.ADMIN);

            memberRepository.stub(member);

            //when
            Long memberId = loginService.login(username, rawPassword);

            //then
            assertThat(memberId).isEqualTo(1);
        }
    }
}