package com.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/circuitBreaker/fallback")
    public Mono<String> foodCircuitBreakerFallback() {
        return Mono.just("Food Service is down. Contact to service provider.");
    }
}
