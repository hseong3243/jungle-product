package com.hseong.jungleproduct.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hseong.jungleproduct.service.ProductDto;
import com.hseong.jungleproduct.service.ProductService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @Test
    @DisplayName("POST /api/products 호출 시 상품을 등록한다.")
    void addProduct() throws Exception {
        //given
        AddProductRequest request = new AddProductRequest(1235L, "미니얼룩말", 24000);

        given(productService.addProduct(anyLong(), anyString(), anyInt())).willReturn(1L);

        //when
        ResultActions result = mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request)));

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
        ResultActions result = mockMvc.perform(get("/api/products"));

        //then
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }
}