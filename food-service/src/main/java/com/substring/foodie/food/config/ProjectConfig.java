package com.substring.foodie.food.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ProjectConfig {

/*    @Bean
    @Profile("dev")
    public TestConfig config() {
        return new TestConfig();
    }*/

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder webClient() {
        return WebClient
                .builder();
                /*.baseUrl("lb://restaurant-service")
                .build();*/
    }
}
