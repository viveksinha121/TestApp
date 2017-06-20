package com.castle.zomatotestapp.model;

/**
 * Created by nightstay on 10/06/17.
 */
public class CuisineItem extends ListItem{

    private String cuisineName;

    public String getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    @Override
    public int getType() {
        return TYPE_CUISINE;
    }
}
