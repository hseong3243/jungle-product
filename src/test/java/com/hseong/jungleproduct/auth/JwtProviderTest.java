package com.hseong.jungleproduct.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;
import static org.junit.jupiter.api.Assertions.*;

import com.hseong.jungleproduct.domain.MemberRole;
import io.jsonwebtoken.ExpiredJwtException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class JwtProviderTest {

    private String issuer;
    private int expirySeconds;
    private int refreshExpirySeconds;
    private String secret;
    private String refreshSecret;
    private JwtProvider jwtProvider;

    @BeforeEach
    void setUp() {
        issuer = "test";
        expirySeconds = 3600;
        refreshExpirySeconds = 18000;
        secret = "thisisjusttestaccesssecretsodontworry";
        refreshSecret = "thisisjusttestrefreshsecretsodontworry";
        jwtProvider
            = new JwtProvider(issuer, expirySeconds, refreshExpirySeconds, secret, refreshSecret);
    }

    @Nested
    @DisplayName("createToken 호출 시")
    class CreateTokenTest {

        @Test
        @DisplayName("액세스 토큰이 만들어진다.")
        void createToken() {
            //given
            Long memberId = 1L;
            MemberRole memberRole = MemberRole.ADMIN;

            //when
            TokenResponse TokenResponse = jwtProvider.createToken(memberId, memberRole);

            //then
            assertThat(TokenResponse.accessToken()).isNotBlank();
        }
    }

    @Nested
    @DisplayName("parseAccessToken 호출 시")
    class ParseAccessTokenTest {

        @Test
        @DisplayName("액세스 토큰을 파싱한 결과를 반환한다.")
        void parseAccessToken() {
            //given
            Long memberId = 1L;
            MemberRole memberRole = MemberRole.ADMIN;
            TokenResponse tokenResponse = jwtProvider.createToken(memberId, memberRole);

            //when
            ClaimsResponse claims = jwtProvider.parseAccessToken(tokenResponse.accessToken());

            //then
            Long findMemberId = claims.memberId();
            List<String> findAuthorities = claims.authorities();
            assertThat(findMemberId).isEqualTo(memberId);
            assertThat(findAuthorities).containsExactlyElementsOf(memberRole.getAuthorities());
        }

        @Test
        @DisplayName("예외(expiredAccessToken): 만료된 토큰")
        void expiredAccessToken_WhenAccessTokenIsExpired() {
            //given
            Long memberId = 1L;
            MemberRole memberRole = MemberRole.ADMIN;
            int alreadyExpiredSeconds = -1;
            JwtProvider expiredJwtProvider = new JwtProvider(issuer, alreadyExpiredSeconds,
                refreshExpirySeconds, secret, refreshSecret);
            TokenResponse tokenResponse = expiredJwtProvider.createToken(memberId, memberRole);
            String expiredAccessToken = tokenResponse.accessToken();

            //when
            Exception exception = catchException(
                () -> jwtProvider.parseAccessToken(expiredAccessToken));

            //then
            assertThat(exception).isInstanceOf(AccessTokenExpiredException.class);
        }
    }
}