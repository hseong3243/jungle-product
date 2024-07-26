package com.hseong.jungleproduct.service.response;

import java.util.List;

public record FindOrdersResponse(
        List<OrderDto> orders,
        int totalPages,
        long totalElements
) {
    public static FindOrdersResponse of(List<OrderDto> content, int totalPages, long totalElements) {
        return new FindOrdersResponse(
                content,
                totalPages,
                totalElements
        );
    }
}
