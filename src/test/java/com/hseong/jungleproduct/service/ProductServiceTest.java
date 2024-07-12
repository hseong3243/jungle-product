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

    @Nested
    @DisplayName("initializeAmount 호출 시")
    class InitializeAmountTest {

        private Product product;

        @BeforeEach
        void setUp() {
            product = new Product(3423L, "미니얼룩말", 24000);
        }

        @Test
        void 상품_재고를_초기화합니다() {
            //given
            productRepository.save(product);

            //when
            productService.initializeAmount(product.getProductId(), 10, 20);

            //then
            assertThat(product.getDisplayAmount()).isEqualTo(10);
            assertThat(product.getStorageAmount()).isEqualTo(20);
        }
    }

    @Nested
    @DisplayName("findProduct 호출 시")
    class FindProductTest {

        private Product product;

        @BeforeEach
        void setUp() {
            product = new Product(2345L, "미니얼룩말", 24000);
            product.initializeInventory(10, 20);
        }

        @Test
        void 단일_상품을_조회합니다() {
            //given
            productRepository.save(product);

            //when
            ProductDto productDto = productService.findProduct(product.getProductId());

            //then
            assertThat(productDto.productId()).isEqualTo(product.getProductId());
            assertThat(productDto.name()).isEqualTo(product.getName());
            assertThat(productDto.price()).isEqualTo(product.getPrice());
            assertThat(productDto.displayAmount()).isEqualTo(product.getDisplayAmount());
            assertThat(productDto.storageAmount()).isEqualTo(product.getStorageAmount());
        }
    }
}