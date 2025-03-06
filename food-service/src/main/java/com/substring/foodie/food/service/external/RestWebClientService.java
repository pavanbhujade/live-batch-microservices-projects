package com.substring.foodie.food.service.external;

import com.substring.foodie.food.config.AppConstants;
import com.substring.foodie.food.dto.RestaurantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class RestWebClientService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public RestaurantDTO getById(String resId) {

        RestaurantDTO restaurantDto = webClientBuilder
                .baseUrl(AppConstants.RESTAURANT_SERVICE_URL)
                .build()
                .get()
                .uri("/api/v1/restaurants/{id}", resId)
                .retrieve()
                .bodyToMono(RestaurantDTO.class)
                .block();

        return restaurantDto;
    }

    // get all restaurants
    public List<RestaurantDTO> getAll() {
        return webClientBuilder
                .baseUrl(AppConstants.RESTAURANT_SERVICE_URL)
                .build()
                .get()
                .uri("/api/v1/restaurants")
                .retrieve()
                .bodyToFlux(RestaurantDTO.class)
                .collectList()
                .block();
    }

    //post request
    public RestaurantDTO createRestaurant(RestaurantDTO newRestaurant) {
        return webClientBuilder
                .baseUrl(AppConstants.RESTAURANT_SERVICE_URL)
                .build()
                .post()
                .uri("/api/v1/restaurants")
                .bodyValue(newRestaurant)
                .header("Athorization", "Bearer asdgag")
                .retrieve()
                .bodyToMono(RestaurantDTO.class)
                .block();
    }

    // non blocking:

    // get by id

    public Mono<RestaurantDTO> getResById(String restId) {
        return webClientBuilder
                .baseUrl(AppConstants.RESTAURANT_SERVICE_URL)
                .build().get()
                .uri("/api/v1/restaurants/{id}", restId)
                .retrieve()
                .bodyToMono(RestaurantDTO.class);
    }


    public Flux<RestaurantDTO> getAllNon() {
        return webClientBuilder
                .baseUrl(AppConstants.RESTAURANT_SERVICE_URL)
                .build().get()
                .uri("/api/v1/restaurants")
                .retrieve()
                .bodyToFlux(RestaurantDTO.class);

    }


}