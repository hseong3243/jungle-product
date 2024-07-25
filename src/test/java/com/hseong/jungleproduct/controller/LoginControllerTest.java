package com.hseong.jungleproduct.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.hseong.jungleproduct.auth.TokenResponse;
import com.hseong.jungleproduct.base.BaseControllerTest;
import com.hseong.jungleproduct.controller.request.LoginRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

class LoginControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("POST /api/login 호출 시 토큰을 생성한다.")
    void login() throws Exception {
        //given
        LoginRequest request = new LoginRequest("username", "password");

        given(loginService.login(anyString(), anyString())).willReturn(new TokenResponse("asdf", "asdf"));

        //when
        ResultActions result = mockMvc.perform(post("/api/login")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(request)));

        //then
        result.andDo(print())
            .andExpect(jsonPath("$.data").exists());
    }
}
