package com.hseong.jungleproduct.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.hseong.jungleproduct.base.BaseIntegrationTest;
import com.hseong.jungleproduct.domain.Order;
import com.hseong.jungleproduct.domain.Product;
import com.hseong.jungleproduct.service.response.FindOrdersResponse;
import com.hseong.jungleproduct.service.response.OrderDto;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class OrderServiceIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

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

            Long orderIdA = orderService.buyProduct(productId, 3);
            Long orderIdB = orderService.buyProduct(productId, 3);
            Long orderIdC = orderService.buyProduct(productId, 3);
            Long orderIdD = orderService.buyProduct(productId, 3);
            Long orderIdE = orderService.buyProduct(productId, 3);
            Long orderIdF = orderService.buyProduct(productId, 3);

            //when
            FindOrdersResponse orders = orderService.findOrders(0, 10);

            //then
            List<OrderDto> orderDtos = orders.orders();
            assertThat(orderDtos).map(OrderDto::orderId)
                    .containsExactly(orderIdF, orderIdE, orderIdD, orderIdC, orderIdB, orderIdA);
        }
    }

    @Nested
    @DisplayName("calculateSummary 호출 시")
    class CalculateSummaryTest {

        @Test
        @DisplayName("해당 날짜의 판매액을 계산한다.")
        void calculateSummaryAtInputDate() {
            //given
            Long productId = productService.addProduct(1234L, "얼룩말", 24000);
            productService.initializeAmount(productId, 30, 50);

            Product product = productRepository.findById(productId).get();
            orderService.buyProduct(productId, 2);
            orderService.buyProduct(productId, 2);
            orderService.buyProduct(productId, 2);
            orderService.buyProduct(productId, 2);

            Order orderYesterday = new Order(null, product, 2, ZonedDateTime.now().minusDays(1));
            Order orderTomorrow = new Order(null, product, 2, ZonedDateTime.now().plusDays(1));
            orderRepository.save(orderYesterday);
            orderRepository.save(orderTomorrow);

            //when
            Long summary = orderService.calculateSummary(LocalDate.now());

            //then
            assertThat(summary).isEqualTo(24000 * 8);
        }
    }
}
