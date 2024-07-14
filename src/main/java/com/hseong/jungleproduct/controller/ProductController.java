package com.hseong.jungleproduct.controller;

import com.hseong.jungleproduct.service.ProductDto;
import com.hseong.jungleproduct.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/products")
    public ApiResponse<Long> addProduct(@RequestBody AddProductRequest request) {
        Long productId = productService.addProduct(request.productId(), request.name(), request.price());
        return ApiResponse.success(productId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/products")
    public ApiResponse<List<ProductDto>> findProducts() {
        List<ProductDto> productDtos = productService.findAll();
        return ApiResponse.success(productDtos);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/products/search")
    public ApiResponse<List<ProductDto>> searchProducts(@RequestParam("productId") Long productId) {
        List<ProductDto> productDtos = productService.searchProduct(productId);
        return ApiResponse.success(productDtos);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/products/{productId}")
    public ApiResponse<String> updateProduct(@PathVariable("productId") Long productId,
                                                 @RequestBody UpdateProductRequest request) {
        productService.initializeAmount(productId, request.displayAmount(), request.storageAmount());
        return ApiResponse.success();
    }
}
