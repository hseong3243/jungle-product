package com.hseong.jungleproduct.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hseong.jungleproduct.base.BaseControllerTest;
import com.hseong.jungleproduct.controller.request.BuyProductRequest;
import com.hseong.jungleproduct.service.response.FindOrdersResponse;
import com.hseong.jungleproduct.service.response.OrderDto;
import com.hseong.jungleproduct.service.response.ProductDto;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
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

    @Test
    @DisplayName("GET /api/orders 호출 시 상품 목록을 조회한다.")
    void findOrders() throws Exception {
        //given
        ProductDto productDto = new ProductDto(1L, 2353L, "미니얼룩말", 23000, 10, 20);
        OrderDto orderDto = new OrderDto(1L, productDto, 2, ZonedDateTime.now());
        FindOrdersResponse findOrdersResponse = new FindOrdersResponse(List.of(orderDto), 1, 1);

        given(orderService.findOrders(anyInt(), anyInt())).willReturn(findOrdersResponse);

        //when
        ResultActions result = mockMvc.perform(get("/api/orders")
                .param("page", "0")
                .param("size", "10"));

        //then
        result.andDo(print());
    }

    @Test
    @DisplayName("GET /api/calculates 호출 시 특정 날짜의 판매 금액을 조죄한다.")
    void calculateOrdersAmount() throws Exception {
        //given
        given(orderService.calculateSummary(any()))
                .willReturn(24000L);

        //when
        ResultActions result = mockMvc.perform(get("/api/calculates")
                        .header(AUTHORIZATION_HEADER, accessToken)
                .param("calculateDate", LocalDate.now().toString()));

        //then
        result.andDo(print())
                .andExpect(status().isOk());
    }
}