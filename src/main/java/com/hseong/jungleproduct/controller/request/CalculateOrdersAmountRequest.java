package com.hseong.jungleproduct.controller.request;

import java.time.LocalDate;

public record CalculateOrdersAmountRequest(LocalDate calculateDate) {
}
