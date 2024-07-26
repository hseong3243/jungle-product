package com.hseong.jungleproduct.controller;

import com.hseong.jungleproduct.auth.AccessTokenExpiredException;
import com.hseong.jungleproduct.auth.InvalidAccessTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiResponse<String> exceptionHandler(Exception e) {
        log.error("예측하지 못한 예외가 발생했습니다.", e);
        return ApiResponse.fail("예측하지 못한 예외가 발생했습니다.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<String> badRequestHandler(IllegalArgumentException e) {
        log.info("올바르지 않는 입력입니다. 메세지={}", e.getMessage());
        return ApiResponse.fail("요청이 올바르지 않습니다.");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchFieldError.class)
    public ApiResponse<String> notFoundHandler(NoSuchFieldError e) {
        log.warn("존재하지 리소스에 요청이 이루어졌습니다. 메세지={}", e.getMessage());
        return ApiResponse.fail("존재하지 않는 리소스입니다.");
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessTokenExpiredException.class)
    public ApiResponse<String> tokenExpiredHandler(AccessTokenExpiredException e) {
        return ApiResponse.fail("액세스 토큰이 만료되었습니다.");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidAccessTokenException.class)
    public ApiResponse<String> invalidTokenHandler(InvalidAccessTokenException e) {
        log.warn("요청 토큰이 올바르지 않습니다. 메세지={}", e.getMessage());
        return ApiResponse.fail("요청 토큰이 올바르지 않습니다.");
    }
}
