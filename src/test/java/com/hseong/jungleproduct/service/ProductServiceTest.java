package com.hseong.jungleproduct.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.hseong.jungleproduct.domain.Product;
import com.hseong.jungleproduct.repository.ProductMemoryRepository;
import com.hseong.jungleproduct.service.response.ProductDto;
import java.util.List;
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
            Optional<Product> optionalProduct = productRepository.findByProductNumber(productId);
            assertThat(optionalProduct).isNotEmpty().get().satisfies(product -> {
                assertThat(product.getProductNumber()).isEqualTo(productId);
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
            Long productId = productRepository.save(product);

            //when
            productService.initializeAmount(productId, 10, 20);

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
            product = new Product(1L, 2345L, "미니얼룩말", 24000, 0, 0);
            product.initializeInventory(10, 20);
        }

        @Test
        void 단일_상품을_조회합니다() {
            //given
            Long productId = productRepository.save(product);

            //when
            ProductDto productDto = productService.findProduct(productId);

            //then
            assertThat(productDto.productNumber()).isEqualTo(product.getProductNumber());
            assertThat(productDto.name()).isEqualTo(product.getName());
            assertThat(productDto.price()).isEqualTo(product.getPrice());
            assertThat(productDto.displayAmount()).isEqualTo(product.getDisplayAmount());
            assertThat(productDto.storageAmount()).isEqualTo(product.getStorageAmount());
        }
    }

    @Nested
    @DisplayName("findAll 호출 시")
    class FindAllTest {

        private Product productA;
        private Product productB;
        private Product productC;
        private Product productD;

        @BeforeEach
        void setUp() {
            productA = new Product(2354L, "미니얼룩말", 24000);
            productB = new Product(2356L, "어흥사자", 36000);
            productC = new Product(2325L, "아기호랑이", 22000);
            productD = new Product(4364L, "아기늑대", 21000);
        }

        @Test
        void 모든_상품을_조회합니다() {
            //given
            productRepository.save(productA);
            productRepository.save(productB);
            productRepository.save(productC);
            productRepository.save(productD);

            //when
            List<ProductDto> productDtos = productService.findAll();

            //then
            assertThat(productDtos).map(ProductDto::name)
                    .containsExactlyInAnyOrder("미니얼룩말", "어흥사자", "아기호랑이", "아기늑대");
        }
    }

    @Nested
    @DisplayName("searchProduct 호출 시")
    class SearchProductTest {

        @Test
        @DisplayName("품번의 프리픽스에 해당하는 상품들을 검색한다.")
        void test() {
            //given
            Product productA = new Product(2335L, "미니얼룩말", 24000);
            Product productB = new Product(2356L, "미니얼룩말", 24000);
            Product productC = new Product(3221L, "미니얼룩말", 24000);
            productRepository.save(productA);
            productRepository.save(productB);
            productRepository.save(productC);

            //when
            List<ProductDto> productDtos = productService.searchProduct(23L);

            //then
            assertThat(productDtos).hasSize(2)
                    .map(ProductDto::productNumber)
                    .contains(productA.getProductNumber(), productB.getProductNumber());
        }
    }
}