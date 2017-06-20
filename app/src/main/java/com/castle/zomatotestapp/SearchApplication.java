package com.castle.zomatotestapp;

import android.app.Application;

import com.castle.zomatotestapp.rest.ApiClient;
import com.castle.zomatotestapp.rest.ApiInterface;

/**
 * Created by nightstay on 10/06/17.
 */
public class SearchApplication extends Application {

    private ApiInterface apiService;
    private static SearchApplication searchApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        searchApplication = this;
        apiService = ApiClient.getClient().create(ApiInterface.class);
    }

    public static SearchApplication getInstance(){
        return searchApplication;
    }

    public ApiInterface getApiService() {
        return apiService;
    }
}
