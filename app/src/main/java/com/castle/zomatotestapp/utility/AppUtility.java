package com.castle.zomatotestapp.utility;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.castle.zomatotestapp.constants.AppConstant;
import com.castle.zomatotestapp.model.ListItem;
import com.castle.zomatotestapp.model.Restaurant;
import com.castle.zomatotestapp.storage.AppStorage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nightstay on 10/06/17.
 */
public class AppUtility {

    public static void showToast(Activity activity, String msg){
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    public static void hideSoftKeyboard(Activity context){
        InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow((context.getCurrentFocus() == null) ? null : context.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void addRestaurantToFavourite(Restaurant restaurant){
        List<Restaurant> list = getFavouriteRestaurants();
        if(!list.contains(restaurant)){
            list.add(restaurant);
            Gson gson = new Gson();
            AppStorage.getInstance().saveStringPreferences(AppConstant.SHARED_PREF_KEY_FAVOURITE, gson.toJson(list));
        }
    }

    public static List<Restaurant> getFavouriteRestaurants(){
        List<Restaurant> list = null;
        if(AppStorage.getInstance().getStringPreference(AppConstant.SHARED_PREF_KEY_FAVOURITE).equalsIgnoreCase("")){
            list = new ArrayList<>();
        }else{
            Gson gson = new Gson();
            Type type = new TypeToken<List<Restaurant>>(){}.getType();
            list = gson.fromJson(AppStorage.getInstance().getStringPreference(AppConstant.SHARED_PREF_KEY_FAVOURITE), type);
        }

        return list;
    }
}
