package com.substring.foodie.food.service.external.fallback;


import com.substring.foodie.food.dto.RestaurantDTO;
import com.substring.foodie.food.service.external.RestaurantService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RestaurantServiceFallback implements RestaurantService {

    @Override
    public RestaurantDTO getById(String restaurantId) {
        System.out.println("Fallback executed...");
        return null;
    }

    @Override
    public List<RestaurantDTO> getAll() {
        return List.of();
    }

    @Override
    public RestaurantDTO create(RestaurantDTO dto) {
        return null;
    }

    @Override
    public void delete(String restaurantId) {

    }
}
