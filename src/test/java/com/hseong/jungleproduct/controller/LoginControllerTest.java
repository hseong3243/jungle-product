package com.hseong.jungleproduct.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.hseong.jungleproduct.auth.TokenResponse;
import com.hseong.jungleproduct.base.BaseControllerTest;
import com.hseong.jungleproduct.controller.request.LoginRequest;
import com.hseong.jungleproduct.domain.Member;
import com.hseong.jungleproduct.domain.MemberRole;
import com.hseong.jungleproduct.service.response.LoginResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

class LoginControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("POST /api/login 호출 시 토큰을 생성한다.")
    void login() throws Exception {
        //given
        LoginRequest request = new LoginRequest("username", "password");

        Member member = new Member(1L, "username", "password", MemberRole.ADMIN);
        TokenResponse tokenResponse = new TokenResponse("asdf", "asdf");
        LoginResponse loginResponse = LoginResponse.of(member, tokenResponse);
        given(loginService.login(anyString(), anyString())).willReturn(loginResponse);

        //when
        ResultActions result = mockMvc.perform(post("/api/login")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(request)));

        //then
        result.andDo(print())
            .andExpect(jsonPath("$.data").exists());
    }
}
