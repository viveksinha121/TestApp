package com.castle.zomatotestapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.castle.zomatotestapp.R;
import com.castle.zomatotestapp.SearchApplication;
import com.castle.zomatotestapp.adapter.GroupedRestaurantAdapter;
import com.castle.zomatotestapp.constants.AppConstant;
import com.castle.zomatotestapp.model.CuisineItem;
import com.castle.zomatotestapp.model.ListItem;
import com.castle.zomatotestapp.model.Restaurant;
import com.castle.zomatotestapp.model.RestaurantResponse;
import com.castle.zomatotestapp.model.RestaurantWrap;
import com.castle.zomatotestapp.rest.ApiInterface;
import com.castle.zomatotestapp.utility.AppUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private ApiInterface apiService;
    private RecyclerView recyclerView;
    private EditText etSearch;
    private Button btnSearch;
    private String searchStr;
    private ProgressDialog progressDialog;
    private MenuItem defaultItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiService = SearchApplication.getInstance().getApiService();

        etSearch = (EditText) findViewById(R.id.et_search);

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    searchRestaurants(etSearch.getText().toString());
                    return true;
                }
                return false;
            }
        });

        btnSearch = (Button) findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Searching restaurants...");

        recyclerView = (RecyclerView) findViewById(R.id.restaurant_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_search:
                searchRestaurants(etSearch.getText().toString());
                break;
        }
    }

    private void searchRestaurants(String str){
        if(str == null || str.equalsIgnoreCase("")){
            AppUtility.showToast(this, "Please enter search text");
        }else{
            searchStr = str;
            if(defaultItem != null && !defaultItem.isChecked()){
                defaultItem.setChecked(true);
            }
            searchRestaurants(str, "0", "", "");
        }
    }


    private void searchRestaurants(String searchString, String start, final String sort, String order){
        AppUtility.hideSoftKeyboard(MainActivity.this);
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("q", searchString);
        queryMap.put("start", start);
        queryMap.put("count", AppConstant.SEARCH_RESULT_COUNT);
        queryMap.put("sort", sort);
        queryMap.put("order", order);
        progressDialog.show();
        Call<RestaurantResponse> call = apiService.gerRestaurants(AppConstant.API_KEY, queryMap);
        call.enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                hideProgressDialog();
                List<RestaurantWrap> restaurantwraps = response.body().getRestaurants();

                if(sort == null || sort.equalsIgnoreCase("")){
                    List<ListItem> groupedList = getGroupedList(restaurantwraps);
                    recyclerView.setAdapter(new GroupedRestaurantAdapter(groupedList));
                }else{
                    List<ListItem> sortedList = getSortedListItems(restaurantwraps);
                    recyclerView.setAdapter(new GroupedRestaurantAdapter(sortedList));
                }

            }

            @Override
            public void onFailure(Call<RestaurantResponse> call, Throwable t) {
                hideProgressDialog();
                Log.e(TAG, t.toString());
                AppUtility.showToast(MainActivity.this, "Something went wrong. Please try again.");
            }
        });
    }

    private void hideProgressDialog(){
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    private List<ListItem> getGroupedList(List<RestaurantWrap> restaurantwraps){
        List<ListItem> list = new ArrayList<>();
        HashMap<String, List<Restaurant>> map = new HashMap<>();
        for(RestaurantWrap restaurantwrap : restaurantwraps){
            Restaurant restaurant = restaurantwrap.getRestaurant();
            String cuisineType = restaurant.getCuisines();
            if(map.containsKey(cuisineType)){
                map.get(cuisineType).add(restaurant);
            }else{
                List<Restaurant> tList = new ArrayList<>();
                tList.add(restaurant);
                map.put(cuisineType, tList);
            }
        }

        for(String cuisine : map.keySet()){
            CuisineItem cuisineItem = new CuisineItem();
            cuisineItem.setCuisineName(cuisine);
            list.add(cuisineItem);
            for(Restaurant r : map.get(cuisine)){
                list.add(r);
            }
        }

        return list;
    }

    private List<ListItem> getSortedListItems(List<RestaurantWrap> restaurantwraps){
        List<ListItem> list = new ArrayList<>();

        for(RestaurantWrap restaurantwrap : restaurantwraps){
            Restaurant restaurant = restaurantwrap.getRestaurant();
            list.add(restaurant);
        }

        return list;
    }



    @Override
    protected void onStop() {
        super.onStop();
        hideProgressDialog();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        defaultItem = menu.findItem(R.id.group_by_cuisine);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.fav:
                startActivity(new Intent(MainActivity.this, FavouriteActivity.class));
                return true;
            case R.id.group_by_cuisine:
                item.setChecked(true);
                searchRestaurants(searchStr);
                return true;
            case R.id.sort_by_price_asc:
                item.setChecked(true);
                searchRestaurants(searchStr, "0", AppConstant.SORT_BY_PRICE, AppConstant.ASC);
                return true;
            case R.id.sort_by_price_desc:
                item.setChecked(true);
                searchRestaurants(searchStr, "0", AppConstant.SORT_BY_PRICE, AppConstant.DESC);
                return true;
            case R.id.sort_by_rating_asc:
                item.setChecked(true);
                searchRestaurants(searchStr, "0", AppConstant.SORT_BY_RATING, AppConstant.ASC);
                return true;
            case R.id.sort_by_rating_desc:
                item.setChecked(true);
                searchRestaurants(searchStr, "0", AppConstant.SORT_BY_RATING, AppConstant.DESC);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
