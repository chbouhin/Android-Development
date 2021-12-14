package com.example.myandroiddevelopment.Models.Account;

import com.google.gson.annotations.SerializedName;

public class MovieAccountStatesWithRatedBoolModel {
    @SerializedName("id")
    public Integer id;
    @SerializedName("favorite")
    public Boolean favorite;
    @SerializedName("rated")
    public Boolean rated;
    @SerializedName("watchlist")
    public Boolean watchlist;
}
