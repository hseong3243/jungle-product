package com.hseong.jungleproduct.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.hseong.jungleproduct.domain.Order;
import com.hseong.jungleproduct.domain.Product;
import com.hseong.jungleproduct.repository.OrderMemoryRepository;
import com.hseong.jungleproduct.repository.ProductMemoryRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

    private OrderService orderService;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        orderRepository = new OrderMemoryRepository();
        productRepository = new ProductMemoryRepository();
        orderService = new OrderService(productRepository, orderRepository);
    }

    @Nested
    @DisplayName("buyProduct 호출 시")
    class BuyProductTest {

        private Product product;

        @BeforeEach
        void setUp() {
            product = new Product(1L, 4038L, "미니 얼룩말", 24000);
            product.initializeInventory(10, 40);
            productRepository.save(product);
        }

        @Test
        void 주문이_생성된다() {
            //given

            //when
            Long orderId = orderService.buyProduct(product.getProductId(), 5);

            //then
            Optional<Order> optionalOrder = orderRepository.findById(orderId);
            assertThat(optionalOrder).isNotEmpty().get().satisfies(order -> {
                assertThat(order.getProduct()).isEqualTo(product);
                assertThat(order.getAmount()).isEqualTo(5);
            });
        }

        @Test
        void 전시된_재고_수량이_감소한다() {
            //given

            //when
            orderService.buyProduct(product.getProductId(), 5);

            //then
            assertThat(product.getDisplayAmount()).isEqualTo(5);
        }

        @Test
        void 창고_수량은_감소하지_않는다() {
            //given

            //when
            orderService.buyProduct(product.getProductId(), 5);

            //then
            assertThat(product.getStorageAmount()).isEqualTo(40);
        }

        @Test
        void 전시된_재고_수량보다_많이_주문하면_음수까지_떨어집니다() {
            //given

            //when
            Long orderId = orderService.buyProduct(product.getProductId(), 20);

            //then
            assertThat(product.getDisplayAmount()).isEqualTo(-10);
        }
    }
}