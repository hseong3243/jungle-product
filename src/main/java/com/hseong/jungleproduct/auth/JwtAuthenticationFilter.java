package com.hseong.jungleproduct.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String HEADER = "Authorization";
    private static final String BEARER = "Bearer ";

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.debug("[Auth] JWT 인증 인터셉터 시작");
        String bearerAccessToken = request.getHeader(HEADER);
        if (bearerAccessToken != null) {
            log.debug("[Auth] JWT 인증 프로세스 시작");
            String accessToken = removeBearer(bearerAccessToken);
            ClaimsResponse claims = jwtProvider.parseAccessToken(accessToken);
            UsernamePasswordAuthenticationToken authentication = UsernamePasswordAuthenticationToken.authenticated(
                    claims.memberId(), accessToken,
                    claims.authorities().stream()
                            .map(
                                    SimpleGrantedAuthority::new).toList());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("[Auth] JWT 인증 프로세스 종료. 사용자 인증됨. {}", authentication);
        }
        log.debug("[Auth] Jwt 인증 인터셉터 종료");
        filterChain.doFilter(request, response);
    }

    private String removeBearer(String bearerAccessToken) {
        if (!bearerAccessToken.contains(BEARER)) {
            throw new RuntimeException("올바르지 않은 액세스 토큰 형식입니다.");
        }
        return bearerAccessToken.replace(BEARER, "");
    }
}
