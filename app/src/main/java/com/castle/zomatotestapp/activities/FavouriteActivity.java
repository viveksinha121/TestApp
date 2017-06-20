package com.castle.zomatotestapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.castle.zomatotestapp.R;
import com.castle.zomatotestapp.adapter.FavouriteRestaurantAdapter;
import com.castle.zomatotestapp.adapter.GroupedRestaurantAdapter;
import com.castle.zomatotestapp.model.ListItem;
import com.castle.zomatotestapp.model.Restaurant;
import com.castle.zomatotestapp.utility.AppUtility;

import java.util.List;

public class FavouriteActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        recyclerView = (RecyclerView) findViewById(R.id.rv_fav);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Restaurant> list = AppUtility.getFavouriteRestaurants();
        if(list == null || list.size() < 1){
            AppUtility.showToast(FavouriteActivity.this, "No Favourite restaurant found");
        }else{
            recyclerView.setAdapter(new FavouriteRestaurantAdapter(list));
        }
    }
}
