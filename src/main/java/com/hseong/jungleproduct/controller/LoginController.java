package com.hseong.jungleproduct.controller;

import com.hseong.jungleproduct.controller.request.LoginRequest;
import com.hseong.jungleproduct.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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
    public ApiResponse<Long> login(HttpServletRequest httpServletRequest, @RequestBody LoginRequest request) {
        Long memberId = loginService.login(request.username(), request.password());
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("memberId", memberId);
        return ApiResponse.success(memberId);
    }
}
