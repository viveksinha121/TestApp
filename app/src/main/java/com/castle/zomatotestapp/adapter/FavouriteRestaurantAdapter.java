package com.castle.zomatotestapp.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.castle.zomatotestapp.R;
import com.castle.zomatotestapp.model.Restaurant;
import com.castle.zomatotestapp.utility.AppUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nightstay on 13/06/17.
 */
public class FavouriteRestaurantAdapter extends RecyclerView.Adapter<FavouriteRestaurantAdapter.FavouriteViewHolder>{

    private List<Restaurant> restaurants;

    public FavouriteRestaurantAdapter(List<Restaurant> list){
        restaurants = list;
    }

    @Override
    public FavouriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FavouriteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_restaurant, parent, false));
    }

    @Override
    public void onBindViewHolder(FavouriteViewHolder holder, int position) {
        final Restaurant restaurant = restaurants.get(position);
        holder.tvRestaurantName.setText(restaurant.getRestaurantName());
        holder.tvCuisineType.setText(restaurant.getCuisines());
        holder.tvAvgCost.setText("Avg. cost (2 person): " + restaurant.getCurrency() + " " + restaurant.getAverageCostForTwo());
        holder.tvAddress.setText(restaurant.getRestaurantLocation().getAddress());
        holder.tvRating.setText(restaurant.getUserRating().getRating());
        holder.tvRating.setBackgroundColor(Color.parseColor("#" + restaurant.getUserRating().getRatingColor()));
        holder.ivFav.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public static class FavouriteViewHolder extends RecyclerView.ViewHolder{
        TextView tvRestaurantName, tvCuisineType, tvAvgCost, tvAddress, tvRating;
        ImageView ivFav;
        public FavouriteViewHolder(View v){
            super(v);
            tvRestaurantName = (TextView) v.findViewById(R.id.tv_restaurant_name);
            tvCuisineType = (TextView) v.findViewById(R.id.tv_cuisine_type);
            tvAvgCost = (TextView) v.findViewById(R.id.tv_avg_cost);
            tvAddress = (TextView) v.findViewById(R.id.tv_address);
            tvRating = (TextView) v.findViewById(R.id.tv_rating);
            ivFav = (ImageView) v.findViewById(R.id.iv_fav);
        }
    }
}
