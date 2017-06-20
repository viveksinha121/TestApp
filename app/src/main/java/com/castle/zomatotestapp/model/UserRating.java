package com.castle.zomatotestapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nightstay on 10/06/17.
 */
public class UserRating {

    @SerializedName("aggregate_rating")
    private String rating;

    @SerializedName("rating_text")
    private String ratingText;

    @SerializedName("rating_color")
    private String ratingColor;

    @SerializedName("votes")
    private String votes;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRatingText() {
        return ratingText;
    }

    public void setRatingText(String ratingText) {
        this.ratingText = ratingText;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }
}
