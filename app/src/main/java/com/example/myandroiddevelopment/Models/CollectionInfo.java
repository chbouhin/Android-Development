package com.example.myandroiddevelopment.Models;

import com.google.gson.annotations.SerializedName;

public class CollectionInfo {
    @SerializedName("id")
    public Integer id;
    @SerializedName("name")
    public String name;
    @SerializedName("poster_path")
    public String poster_path;
    @SerializedName("backdrop_path")
    public String backdrop_path;
}
