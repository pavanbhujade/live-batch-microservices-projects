package com.substring.foodie.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.substring.foodie.restaurant.entities.Restaurant;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, String> {

    List<Restaurant> findByName(String name);
}
