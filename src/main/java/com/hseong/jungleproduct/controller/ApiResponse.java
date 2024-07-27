package com.hseong.jungleproduct.controller;

public record ApiResponse<T>(T data) {

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data);
    }

    public static ApiResponse<String> success() {
        return success("");
    }

    public static ApiResponse<String> fail(String message) {
        return new ApiResponse<>(message);
    }
}
