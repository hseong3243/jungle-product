package com.hseong.jungleproduct.controller.request;

public record AddProductRequest(Long productNumber, String name, int price) {
}
