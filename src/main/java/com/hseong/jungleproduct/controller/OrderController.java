package com.hseong.jungleproduct.controller;

import com.hseong.jungleproduct.controller.request.BuyProductRequest;
import com.hseong.jungleproduct.controller.request.CalculateOrdersAmountRequest;
import com.hseong.jungleproduct.controller.request.FindOrderRequest;
import com.hseong.jungleproduct.service.OrderService;
import com.hseong.jungleproduct.service.response.FindOrdersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/orders")
    public ApiResponse<FindOrdersResponse> findOrders(FindOrderRequest request) {
        return ApiResponse.success(orderService.findOrders(request.page(), request.size()));
    }

    @GetMapping("/calculates")
    public ApiResponse<Long> calculateOrdersAmount(CalculateOrdersAmountRequest request) {
        return ApiResponse.success(orderService.calculateSummary(request.calculateDate()));
    }
}
