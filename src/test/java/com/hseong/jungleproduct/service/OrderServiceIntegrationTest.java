package com.hseong.jungleproduct.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.hseong.jungleproduct.domain.Product;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderServiceIntegrationTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductRepository productRepository;

    @Nested
    @DisplayName("buyProduct 호출 시")
    class BuyProductTest {

        @Test
        @DisplayName("상품의 재고가 업데이트 된다.")
        void updateProductAmount() {
            //given
            Product product = new Product(1234L, "미니얼룩말", 24000);
            product.initializeInventory(5, 20);
            productRepository.save(product);

            //when
            orderService.buyProduct(product.getProductNumber(), 3);

            //then
            List<Product> products = productRepository.findAll();
            assertThat(products).hasSize(1)
                    .first().satisfies(findProduct -> {
                        assertThat(findProduct.getProductNumber()).isEqualTo(product.getProductNumber());
                        assertThat(findProduct.getPrice()).isEqualTo(product.getPrice());
                        assertThat(findProduct.getDisplayAmount()).isEqualTo(2);
                        assertThat(findProduct.getStorageAmount()).isEqualTo(product.getStorageAmount());
                    });
        }
    }
}