package com.example.myandroiddevelopment.Models.Account;

import com.example.myandroiddevelopment.Models.Post.RateModel;
import com.google.gson.annotations.SerializedName;

public class MovieAccountStatesModel {
    @SerializedName("id")
    public Integer id;
    @SerializedName("favorite")
    public Boolean favorite;
    @SerializedName("rated")
    public RateModel rated;
    @SerializedName("watchlist")
    public Boolean watchlist;
}
