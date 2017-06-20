package com.castle.zomatotestapp.model;

/**
 * Created by nightstay on 10/06/17.
 */
public abstract class ListItem {
    public static final int TYPE_CUISINE = 0;
    public static final int TYPE_RESTAURANT = 1;

    abstract public int getType();
}
