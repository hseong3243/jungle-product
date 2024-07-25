package com.hseong.jungleproduct.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hseong.jungleproduct.base.BaseControllerTest;
import com.hseong.jungleproduct.controller.request.AddProductRequest;
import com.hseong.jungleproduct.controller.request.UpdateProductRequest;
import com.hseong.jungleproduct.service.ProductDto;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest
class ProductControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("POST /api/products 호출 시 상품을 등록한다.")
    void addProduct() throws Exception {
        //given
        AddProductRequest request = new AddProductRequest(1235L, "미니얼룩말", 24000);

        given(productService.addProduct(anyLong(), anyString(), anyInt())).willReturn(1L);

        //when
        ResultActions result = mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request))
                .header(AUTHORIZATION_HEADER, accessToken));

        //then
        result.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data").value(1));
    }


    @Test
    @DisplayName("GET /api/products 호출 시 모든 상품 목록을 반환한다.")
    void findProducts() throws Exception {
        //given
        ProductDto productDto = new ProductDto(1L, "미니얼룩말", 24000, 10, 30);
        List<ProductDto> products = List.of(productDto);

        given(productService.findAll()).willReturn(products);

        //when
        ResultActions result = mockMvc.perform(get("/api/products")
                .header(AUTHORIZATION_HEADER, accessToken));

        //then
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    @DisplayName("GET /api/products/search 호출 시 상품 품번 프리픽스와 일치하는 상품 목록을 반환한다.")
    void test() throws Exception {
        //given
        Long productIdPrefix = 23L;
        ProductDto productDto = new ProductDto(1L, "미니얼룩말", 24000, 10, 30);
        List<ProductDto> products = List.of(productDto);

        given(productService.searchProduct(anyLong())).willReturn(products);

        //when
        ResultActions result = mockMvc.perform(get("/api/products/search")
                .header(AUTHORIZATION_HEADER, accessToken)
                .param("productId", String.valueOf(productIdPrefix)));

        //then
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    @DisplayName("PATCH /api/products/{productId} 호출 시 상품 재고를 업데이트한다.")
    void updateProduct() throws Exception {
        //given
        UpdateProductRequest request = new UpdateProductRequest(10, 30);

        //when
        ResultActions result = mockMvc.perform(patch("/api/products/{productId}", 1L)
                .header(AUTHORIZATION_HEADER, accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request)));

        //then
        result.andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    @DisplayName("GET /api/products/{productId} 호출 시 단일 상품 정보를 반환한다.")
    void findProduct() throws Exception {
        //given
        ProductDto productDto = new ProductDto(1235L, "미니얼룩말", 24000, 10, 30);

        given(productService.findProduct(anyLong())).willReturn(productDto);

        //when
        ResultActions result = mockMvc.perform(get("/api/products/{productId}", 1L)
                .header(AUTHORIZATION_HEADER, accessToken));

        //then
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isNotEmpty());
    }
}