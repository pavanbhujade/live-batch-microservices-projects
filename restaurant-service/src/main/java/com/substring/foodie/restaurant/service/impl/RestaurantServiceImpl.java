package com.substring.foodie.restaurant.service.impl;

import com.substring.foodie.restaurant.dto.RestaurantDTO;
import com.substring.foodie.restaurant.entities.Restaurant;
import com.substring.foodie.restaurant.repository.RestaurantRepository;
import com.substring.foodie.restaurant.service.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    // Convert entity to DTO
    private RestaurantDTO convertToDTO(Restaurant restaurant) {
        RestaurantDTO dto = new RestaurantDTO();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setAddress(restaurant.getAddress());
        dto.setPhone(restaurant.getPhone());
        dto.setPictures(restaurant.getPictures());
        dto.setOpen(restaurant.isOpen());
        dto.setOpenTime(restaurant.getOpenTime());
        dto.setCloseTime(restaurant.getCloseTime());
        dto.setAboutRestaurant(restaurant.getAboutRestaurant());
        return dto;
    }

    // Convert DTO to entity
    private Restaurant convertToEntity(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantDTO.getId());
        restaurant.setName(restaurantDTO.getName());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setPhone(restaurantDTO.getPhone());
        restaurant.setPictures(restaurantDTO.getPictures());
        restaurant.setOpen(restaurantDTO.isOpen());
        restaurant.setOpenTime(restaurantDTO.getOpenTime());
        restaurant.setCloseTime(restaurantDTO.getCloseTime());
        restaurant.setAboutRestaurant(restaurantDTO.getAboutRestaurant());
        return restaurant;
    }

    @Override
    public RestaurantDTO save(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = convertToEntity(restaurantDTO);
        restaurant.setId(UUID.randomUUID().toString());
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return convertToDTO(savedRestaurant);
    }

    @Override
    public RestaurantDTO update(RestaurantDTO restaurantDTO, String id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if (optionalRestaurant.isPresent()) {
            Restaurant existingRestaurant = optionalRestaurant.get();
            existingRestaurant.setName(restaurantDTO.getName());
            existingRestaurant.setAddress(restaurantDTO.getAddress());
            existingRestaurant.setPhone(restaurantDTO.getPhone());
            existingRestaurant.setPictures(restaurantDTO.getPictures());
            existingRestaurant.setOpen(restaurantDTO.isOpen());
            existingRestaurant.setOpenTime(restaurantDTO.getOpenTime());
            existingRestaurant.setCloseTime(restaurantDTO.getCloseTime());
            existingRestaurant.setAboutRestaurant(restaurantDTO.getAboutRestaurant());
            restaurantRepository.save(existingRestaurant);
            return convertToDTO(existingRestaurant);
        } else {
            return null; // or throw a custom exception
        }
    }

    // Fetch all restaurants from the database
    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

    }

    @Override
    public Optional<RestaurantDTO> getById(String id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return restaurant.map(this::convertToDTO);
    }

    @Override
    public List<RestaurantDTO> findByName(String name) {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .filter(restaurant -> restaurant.getName().equalsIgnoreCase(name))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        restaurantRepository.deleteById(id);
    }
}
