package com.castle.zomatotestapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nightstay on 10/06/17.
 */
public class RestaurantWrap {

    @SerializedName("restaurant")
    private Restaurant restaurant;

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
