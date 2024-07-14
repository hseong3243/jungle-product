package com.hseong.jungleproduct.controller;

import com.hseong.jungleproduct.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/orders")
    public ApiResponse<Long> buyProduct(@RequestBody BuyProductRequest request) {
        Long orderId = orderService.buyProduct(request.productId(), request.amount());
        return ApiResponse.success(orderId);
    }
}
