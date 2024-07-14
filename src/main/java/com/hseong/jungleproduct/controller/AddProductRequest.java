package com.hseong.jungleproduct.controller;

public record AddProductRequest(Long productId, String name, int price) {
}
