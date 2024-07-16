package com.hseong.jungleproduct.service;

import com.hseong.jungleproduct.domain.Order;
import com.hseong.jungleproduct.domain.Product;
import jakarta.transaction.Transactional;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public Long buyProduct(Long productNumber, int amount) {
        Product product = productRepository.findByProductNumber(productNumber)
                .orElseThrow(NoSuchElementException::new);
        Order order = Order.buy(product, amount);
        return orderRepository.save(order);
    }
}
