package com.hseong.jungleproduct.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.hseong.jungleproduct.domain.Product;
import com.hseong.jungleproduct.repository.ProductMemoryRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ProductServiceTest {

    private ProductService productService;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductMemoryRepository();
        productService = new ProductService(productRepository);
    }

    @Nested
    @DisplayName("addProduct 호출 시")
    class AddProductTest {

        @Test
        void 상품이_생성된다() {
            //given
            Long productId = 4325L;
            String name = "미니얼룩말";
            int price = 24000;

            //when
            Long savedProductId = productService.addProduct(productId, name, price);

            //then
            Optional<Product> optionalProduct = productRepository.findById(productId);
            assertThat(optionalProduct).isNotEmpty().get().satisfies(product -> {
                assertThat(product.getProductId()).isEqualTo(productId);
                assertThat(product.getName()).isEqualTo(name);
                assertThat(product.getPrice()).isEqualTo(price);
            });
        }
    }
}