package com.hseong.jungleproduct.service;

public interface PasswordEncoder {

    String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);
}
