package com.hseong.jungleproduct.domain;

import com.hseong.jungleproduct.auth.InvalidAccessTokenException;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
public enum MemberRole {
    ADMIN("ROLE_ADMIN");

    private final String value;

    MemberRole(String value) {
        this.value = value;
    }

    public List<String> getAuthorities() {
        return List.of(value);
    }

    public static MemberRole fromValue(String value) {
        return Arrays.stream(values())
            .filter(role -> role.value.equals(value))
            .findFirst()
            .orElseThrow(() -> new InvalidAccessTokenException("존재하지 않는 역할입니다."));
    }
}
