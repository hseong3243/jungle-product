package com.hseong.jungleproduct.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.hseong.jungleproduct.base.BaseIntegrationTest;
import com.hseong.jungleproduct.service.response.ProductDto;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ProductIntegrationServiceTest extends BaseIntegrationTest {

    @Autowired
    private ProductService productService;

    @Nested
    @DisplayName("searchProduct 호출 시")
    class SearchProductTest {

        @Test
        @DisplayName("빈 문자열이면 모든 상품을 조회한다.")
        void findProducts_WhenKeywordIsEmpty() {
            //given
            productService.addProduct(1234L, "얼룩말", 24000);
            productService.addProduct(4565L, "기린", 24000);

            //when
            List<ProductDto> result = productService.searchProduct("");

            //then
            assertThat(result).hasSize(2);
        }

        @Test
        @DisplayName("공백이면 모든 상품을 조회한다.")
        void findProducts_WhenKeywordIsBlank() {
            //given
            productService.addProduct(1234L, "얼룩말", 24000);
            productService.addProduct(4565L, "기린", 24000);

            //when
            List<ProductDto> result = productService.searchProduct("   ");

            //then
            assertThat(result).hasSize(2);
        }

        @Test
        @DisplayName("입력된 문자열로 시작하는 상품을 조회한다.")
        void searchProducts() {
            //given
            productService.addProduct(1234L, "얼룩말", 24000);
            productService.addProduct(4565L, "기린", 24000);

            //when
            List<ProductDto> result = productService.searchProduct("12");

            //then
            assertThat(result).hasSize(1);
        }
    }
}
