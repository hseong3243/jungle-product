package com.hseong.jungleproduct.auth;

import com.hseong.jungleproduct.service.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MockPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(String rawPassword) {
        return new StringBuilder(rawPassword).reverse().toString();
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
