package com.substring.foodie.restaurant.service;

import com.substring.foodie.restaurant.dto.RestaurantDTO;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    RestaurantDTO save(RestaurantDTO restaurantDTO);
    List<RestaurantDTO> getAllRestaurants();
    RestaurantDTO update(RestaurantDTO restaurantDTO, String id);
    Optional<RestaurantDTO> getById(String id);
    List<RestaurantDTO> findByName(String name);
    void delete(String id);
}
