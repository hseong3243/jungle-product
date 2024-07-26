package com.hseong.jungleproduct.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

import com.hseong.jungleproduct.auth.JwtProvider;
import com.hseong.jungleproduct.domain.Member;
import com.hseong.jungleproduct.domain.MemberRole;
import com.hseong.jungleproduct.service.response.LoginResponse;
import com.hseong.jungleproduct.stub.MemberStubRepository;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LoginServiceTest {

    private LoginService loginService;
    private MemberStubRepository memberRepository;
    private JwtProvider jwtProvider;
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
        jwtProvider = new JwtProvider(
                "test",
                600,
                1200,
                "asdfasdfadsfasdfasfasdfasdafdfaafsd",
                "asdfasdfasdfasdfasdfasdfadsfasdfafdfasdfas"
        );
        loginService = new LoginService(memberRepository, passwordEncoder, jwtProvider);
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
        @DisplayName("아이디, 패스워드가 일치하면 토큰를 반환한다.")
        void login() {
            //given
            String username = "username";
            String rawPassword = "password";
            String encodedPassword = passwordEncoder.encode(rawPassword);
            Member member = new Member(1L, username, encodedPassword, MemberRole.ADMIN);

            memberRepository.stub(member);

            //when
            LoginResponse response = loginService.login(username, rawPassword);

            //then
            assertThat(response.memberId()).isEqualTo(member.getMemberId());
            assertThat(response.accessToken()).isNotBlank();
        }
    }
}