package com.substring.foodie.food.service.external;

import com.substring.foodie.food.config.AppConstants;
import com.substring.foodie.food.dto.RestaurantDTO;
import com.substring.foodie.food.service.external.fallback.RestaurantServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@FeignClient(name = "restaurant-service", url = "http://restaurant-service")
@FeignClient(name = AppConstants.RESTAURANT_SERVICE_NAME, fallback = RestaurantServiceFallback.class)
public interface RestaurantService {

    //Get restaurant with given id
    @GetMapping("/api/v1/restaurants/{id}")
    RestaurantDTO getById(@PathVariable("id") String restaurantId);

    //Get All
    @GetMapping("/api/v1/restaurants")
    List<RestaurantDTO> getAll();

    // post api

    @PostMapping("/api/v1/restaurants")
    RestaurantDTO create(@RequestBody RestaurantDTO dto);

    // delete

    @DeleteMapping("/api/v1/restaurants/{id}")
    void delete(@PathVariable("id") String restaurantId);

    // update
}
