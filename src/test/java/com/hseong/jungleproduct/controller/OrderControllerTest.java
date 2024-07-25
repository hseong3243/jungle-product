package com.hseong.jungleproduct.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hseong.jungleproduct.base.BaseControllerTest;
import com.hseong.jungleproduct.controller.request.BuyProductRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

class OrderControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("POST /api/orders 호출 시 상품을 구매한다.")
    void buyProduct() throws Exception {
        //given
        BuyProductRequest request = new BuyProductRequest(3425L, 5);

        given(orderService.buyProduct(anyLong(), anyInt())).willReturn(1L);

        //when
        ResultActions result = mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request))
                .header(AUTHORIZATION_HEADER, accessToken));

        //then
        result.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data").value(1));
    }
}