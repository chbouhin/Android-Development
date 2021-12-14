package com.example.myandroiddevelopment.Models.Post;

import com.google.gson.annotations.SerializedName;

public class AddWatchlistModel {
    @SerializedName("media_type")
    public String media_type;
    @SerializedName("media_id")
    public Integer media_id;
    @SerializedName("watchlist")
    public Boolean watchlist;
}
