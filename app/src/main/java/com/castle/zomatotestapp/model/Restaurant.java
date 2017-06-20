package com.castle.zomatotestapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Created by nightstay on 10/06/17.
 */
public class Restaurant extends ListItem{

    @SerializedName("id")
    private String restaurantId;

    @SerializedName("name")
    private String restaurantName;

    @SerializedName("cuisines")
    private String cuisines;

    @SerializedName("average_cost_for_two")
    private String averageCostForTwo;

    @SerializedName("currency")
    private String currency;

    @SerializedName("thumb")
    private String thumb;

    @SerializedName("location")
    private RestaurantLocation restaurantLocation;

    @SerializedName("user_rating")
    private UserRating userRating;

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public String getAverageCostForTwo() {
        return averageCostForTwo;
    }

    public void setAverageCostForTwo(String averageCostForTwo) {
        this.averageCostForTwo = averageCostForTwo;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public RestaurantLocation getRestaurantLocation() {
        return restaurantLocation;
    }

    public void setRestaurantLocation(RestaurantLocation restaurantLocation) {
        this.restaurantLocation = restaurantLocation;
    }

    public UserRating getUserRating() {
        return userRating;
    }

    public void setUserRating(UserRating userRating) {
        this.userRating = userRating;
    }

    @Override
    public int getType() {
        return TYPE_RESTAURANT;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj instanceof Restaurant){
            Restaurant t = (Restaurant) obj;
            if(t.getRestaurantId().equalsIgnoreCase(getRestaurantId())){
                return true;
            }
        }
        return false;
    }
}
