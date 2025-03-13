package com.substring.foodie.restaurant.controller;

import com.substring.foodie.restaurant.dto.RestaurantDTO;
import com.substring.foodie.restaurant.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    // Create a new restaurant
    @PostMapping
    public ResponseEntity<RestaurantDTO> saveRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO savedRestaurant = restaurantService.save(restaurantDTO);
        return new ResponseEntity<>(savedRestaurant, HttpStatus.CREATED);
    }

    // Update an existing restaurant
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@RequestBody RestaurantDTO restaurantDTO, @PathVariable String id) {
        RestaurantDTO updatedRestaurant = restaurantService.update(restaurantDTO, id);
        if (updatedRestaurant != null) {
            return new ResponseEntity<>(updatedRestaurant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to get all restaurants
    @GetMapping
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    int counter=0;
    // Get restaurant by ID
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable String id) {
        Optional<RestaurantDTO> restaurantDTO = restaurantService.getById(id);
        System.out.println("Retried...");
        counter++;
        if(counter<=3){
            System.out.println("Retrying : " + counter);
            throw new RuntimeException("Service down");
        }
        return restaurantDTO.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get restaurants by name
    @GetMapping("/search")
    public ResponseEntity<List<RestaurantDTO>> getRestaurantsByName(@RequestParam String name) {
        List<RestaurantDTO> restaurants = restaurantService.findByName(name);
        return restaurants.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    // Delete a restaurant by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable String id) {
        restaurantService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
