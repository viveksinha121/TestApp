package com.castle.zomatotestapp.rest;

import com.castle.zomatotestapp.model.RestaurantResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;

/**
 * Created by nightstay on 10/06/17.
 */
public interface ApiInterface {

    @GET("search")
    Call<RestaurantResponse> gerRestaurants(@Header("user-key") String user_key, @QueryMap Map<String, String> options);
}

