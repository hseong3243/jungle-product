package com.hseong.jungleproduct.controller;

import com.hseong.jungleproduct.auth.TokenResponse;
import com.hseong.jungleproduct.controller.request.LoginRequest;
import com.hseong.jungleproduct.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ApiResponse<TokenResponse> login(@RequestBody LoginRequest request) {
        TokenResponse token = loginService.login(request.username(), request.password());
        return ApiResponse.success(token);
    }
}
