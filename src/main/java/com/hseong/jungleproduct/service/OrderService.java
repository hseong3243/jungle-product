package com.hseong.jungleproduct.service;

import com.hseong.jungleproduct.domain.Order;
import com.hseong.jungleproduct.domain.Product;
import com.hseong.jungleproduct.service.response.FindOrdersResponse;
import com.hseong.jungleproduct.service.response.OrderDto;
import jakarta.transaction.Transactional;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public Long buyProduct(Long productId, int amount) {
        Product product = productRepository.findById(productId)
                .orElseThrow(NoSuchElementException::new);
        Order order = Order.buy(product, amount);
        return orderRepository.save(order);
    }

    public FindOrdersResponse findOrders(int page, int size) {
        Page<OrderDto> orderDtoPage = orderRepository.findOrdersOrderByCreatedAt(page, size)
                .map(OrderDto::from);
        return FindOrdersResponse.of(orderDtoPage.getContent(), orderDtoPage.getTotalPages(),
                orderDtoPage.getTotalElements());
    }
}
