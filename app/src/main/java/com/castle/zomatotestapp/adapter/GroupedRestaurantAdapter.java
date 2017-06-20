package com.castle.zomatotestapp.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.castle.zomatotestapp.R;
import com.castle.zomatotestapp.model.CuisineItem;
import com.castle.zomatotestapp.model.ListItem;
import com.castle.zomatotestapp.model.Restaurant;
import com.castle.zomatotestapp.utility.AppUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nightstay on 10/06/17.
 */
public class GroupedRestaurantAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ListItem> groupedList;

    public GroupedRestaurantAdapter(List<ListItem> groupedList) {
        this.groupedList = groupedList;
    }


    @Override
    public int getItemViewType(int position) {
        return groupedList.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return groupedList != null ? groupedList.size() : 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {

            case ListItem.TYPE_CUISINE:
                View v1 = inflater.inflate(R.layout.list_item_cuisine, parent, false);
                viewHolder = new CuisineViewHolder(v1);
                break;

            case ListItem.TYPE_RESTAURANT:
                View v2 = inflater.inflate(R.layout.list_item_restaurant, parent, false);
                viewHolder = new RestaurantViewHolder(v2);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        switch (viewHolder.getItemViewType()) {

            case ListItem.TYPE_RESTAURANT:
                final Restaurant restaurant = (Restaurant) groupedList.get(position);
                final RestaurantViewHolder restaurantViewHolder = (RestaurantViewHolder) viewHolder;

                restaurantViewHolder.tvRestaurantName.setText(restaurant.getRestaurantName());
                restaurantViewHolder.tvCuisineType.setText(restaurant.getCuisines());
                restaurantViewHolder.tvAvgCost.setText("Avg. cost (2 person): " + restaurant.getCurrency() + " " + restaurant.getAverageCostForTwo());
                restaurantViewHolder.tvAddress.setText(restaurant.getRestaurantLocation().getAddress());
                restaurantViewHolder.tvRating.setText(restaurant.getUserRating().getRating());
                restaurantViewHolder.tvRating.setBackgroundColor(Color.parseColor("#" + restaurant.getUserRating().getRatingColor()));
                restaurantViewHolder.ivFav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        restaurantViewHolder.ivFav.setImageResource(android.R.drawable.star_on);
                        AppUtility.addRestaurantToFavourite(restaurant);
                    }
                });
                break;

            case ListItem.TYPE_CUISINE:
                CuisineItem cuisineItem = (CuisineItem) groupedList.get(position);
                CuisineViewHolder cuisineViewHolder = (CuisineViewHolder) viewHolder;
                cuisineViewHolder.tvCuisine.setText(cuisineItem.getCuisineName());
                break;
        }
    }
}

class CuisineViewHolder extends RecyclerView.ViewHolder {

    TextView tvCuisine;

    public CuisineViewHolder(View v) {
        super(v);
        tvCuisine = (TextView)v.findViewById(R.id.tv_cuisine);
    }
}

class RestaurantViewHolder extends RecyclerView.ViewHolder {

    TextView tvRestaurantName, tvCuisineType, tvAvgCost, tvAddress, tvRating;
    ImageView ivFav;

    public RestaurantViewHolder(View v) {
        super(v);
        tvRestaurantName = (TextView) v.findViewById(R.id.tv_restaurant_name);
        tvCuisineType = (TextView) v.findViewById(R.id.tv_cuisine_type);
        tvAvgCost = (TextView) v.findViewById(R.id.tv_avg_cost);
        tvAddress = (TextView) v.findViewById(R.id.tv_address);
        tvRating = (TextView) v.findViewById(R.id.tv_rating);
        ivFav = (ImageView) v.findViewById(R.id.iv_fav);
    }
}
