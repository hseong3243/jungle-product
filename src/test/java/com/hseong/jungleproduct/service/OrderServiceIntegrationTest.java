package com.hseong.jungleproduct.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.hseong.jungleproduct.base.DatabaseCleaner;
import com.hseong.jungleproduct.domain.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderServiceIntegrationTest {

    @Autowired
    EntityManagerFactory emf;

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        DatabaseCleaner databaseCleaner = new DatabaseCleaner(em);
        databaseCleaner.clear();
        em.getTransaction().commit();
    }

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Nested
    @DisplayName("buyProduct 호출 시")
    class BuyProductTest {

        @Test
        @DisplayName("상품의 재고가 업데이트 된다.")
        void updateProductAmount() {
            //given
            Product product = new Product(1234L, "미니얼룩말", 24000);
            product.initializeInventory(5, 20);
            Long productId = productRepository.save(product);

            //when
            orderService.buyProduct(productId, 3);

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

    @Nested
    @DisplayName("findOrders 호출 시")
    class FindOrdersTest {

        @Test
        @DisplayName("최근 순서로 주문 내역을 조회해온다.")
        void findOrders() {
            //given
            Long productId = productService.addProduct(4352L, "미니얼룩말", 24000);
            productService.initializeAmount(productId, 30, 50);

            productService.findProduct(productId);

            //when

            //then

        }
    }
}