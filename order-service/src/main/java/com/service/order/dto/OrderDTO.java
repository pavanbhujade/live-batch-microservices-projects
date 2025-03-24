package com.service.order.dto;

public record OrderDTO(
        String orderId,
        int amount,
        String status
) {
}
