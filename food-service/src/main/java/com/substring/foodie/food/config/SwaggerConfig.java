package com.substring.foodie.food.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.substring.foodie.food.controller")) // Specify base package for your controllers
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().title("Food Service").version("1.0").description("API Documentation").build());
    }
}
