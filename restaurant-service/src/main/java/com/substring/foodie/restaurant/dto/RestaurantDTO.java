package com.substring.foodie.restaurant.dto;



import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RestaurantDTO {

    private String id;
    private String name;
    private String address;
    private String phone;

    private List<String> pictures = new ArrayList<>();
    private boolean open = false;
    private LocalTime openTime;
    private LocalTime closeTime;

    private String aboutRestaurant;

}
