package com.substring.foodie.payment.fubctions;

public record OrderDTO(
        String orderId,
        int amount,
        String status
) {
}
