package com.example.myandroiddevelopment.Models;

import com.google.gson.annotations.SerializedName;

public class CollectionInfoModel {
    @SerializedName("id")
    public Integer id;
    @SerializedName("name")
    public String name;
    @SerializedName("poster_path")
    public String poster_path;
    @SerializedName("backdrop_path")
    public String backdrop_path;
}
