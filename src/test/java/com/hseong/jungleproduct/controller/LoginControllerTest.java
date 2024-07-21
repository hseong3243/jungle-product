package com.hseong.jungleproduct.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.hseong.jungleproduct.base.BaseControllerTest;
import com.hseong.jungleproduct.controller.request.LoginRequest;
import jakarta.servlet.http.HttpSession;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

class LoginControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("POST /api/login 호출 시 세션을 생성한다.")
    void login() throws Exception {
        //given
        LoginRequest request = new LoginRequest("username", "password");

        given(loginService.login(anyString(), anyString())).willReturn(1L);

        //when
        ResultActions result = mockMvc.perform(post("/api/login")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(request)));

        //then
        result.andDo(print())
            .andDo(r -> {
                HttpSession session = r.getRequest().getSession(false);
                assertThat(session.getAttribute("memberId")).isEqualTo(1L);
            })
            .andExpect(jsonPath("$.data").isNumber());
    }
}
