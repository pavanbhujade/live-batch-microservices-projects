package com.service.order.functions;

import com.service.order.dto.OrderDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class OrderFunctions {
    @Bean
    public Supplier<String> test() {
        return () -> "Hello World....; How are you?";
    }

    @Bean
    public Function<String, String> test2() {
        return String::toUpperCase;
    }

    @Bean
    public Function<OrderDTO, String> createOrder() {
        return orderDTO -> {
            System.out.println("Creating Order");
            System.out.println(orderDTO.orderId());
            System.out.println(orderDTO.amount());
            System.out.println(orderDTO.status());
            return "Order created with Id : " + orderDTO.orderId();
        };
    }
}
