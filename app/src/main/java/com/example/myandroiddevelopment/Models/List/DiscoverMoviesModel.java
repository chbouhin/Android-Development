package com.example.myandroiddevelopment.Models.List;

import com.example.myandroiddevelopment.Models.Movie.MovieModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DiscoverMoviesModel {
    @SerializedName("page")
    public Integer page;
    @SerializedName("results")
    public List<MovieModel> results;
    @SerializedName("total_results")
    public Integer total_results;
    @SerializedName("total_pages")
    public Integer total_pages;
}
