package com.castle.zomatotestapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nightstay on 10/06/17.
 */
public class RestaurantLocation {

    @SerializedName("address")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
