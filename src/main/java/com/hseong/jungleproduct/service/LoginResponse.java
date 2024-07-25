package com.hseong.jungleproduct.service;

import com.hseong.jungleproduct.auth.TokenResponse;
import com.hseong.jungleproduct.domain.Member;

public record LoginResponse(Long memberId, String accessToken, String refreshToken) {

    public static LoginResponse of(Member member, TokenResponse token) {
        return new LoginResponse(member.getMemberId(), token.accessToken(), token.refreshToken());
    }
}
