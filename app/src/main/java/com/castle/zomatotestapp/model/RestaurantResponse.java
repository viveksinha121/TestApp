package com.castle.zomatotestapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nightstay on 10/06/17.
 */
public class RestaurantResponse {

    @SerializedName("results_found")
    private int resultsFound;

    @SerializedName("results_start")
    private String resultsStart;

    @SerializedName("results_shown")
    private int resultsShown;

    @SerializedName("restaurants")
    private List<RestaurantWrap> restaurants;

    public int getResultsFound() {
        return resultsFound;
    }

    public void setResultsFound(int resultsFound) {
        this.resultsFound = resultsFound;
    }

    public String getResultsStart() {
        return resultsStart;
    }

    public void setResultsStart(String resultsStart) {
        this.resultsStart = resultsStart;
    }

    public int getResultsShown() {
        return resultsShown;
    }

    public void setResultsShown(int resultsShown) {
        this.resultsShown = resultsShown;
    }

    public List<RestaurantWrap> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<RestaurantWrap> restaurants) {
        this.restaurants = restaurants;
    }
}
