package com.hseong.jungleproduct.controller;

public record ApiResponse<T>(T data) {

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data);
    }
}
