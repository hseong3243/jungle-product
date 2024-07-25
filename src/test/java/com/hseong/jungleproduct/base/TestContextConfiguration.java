package com.hseong.jungleproduct.base;

import com.hseong.jungleproduct.auth.JwtProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestContextConfiguration {
    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry-seconds}")
    private int expirySeconds;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.refresh-expiry-seconds}")
    private int refreshExpireSeconds;

    @Value("${jwt.secret}")
    private String refreshSecret;

    @Bean
    public JwtProvider jwtProvider() {
        return new JwtProvider(issuer, expirySeconds, refreshExpireSeconds, secret, refreshSecret);
    }
}
