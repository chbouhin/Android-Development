package com.example.myandroiddevelopment.Models.List;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ResultsDiscoverMovies {
    @SerializedName("poster_path")
    public String poster_path;
    @SerializedName("adult")
    public Boolean adult;
    @SerializedName("overview")
    public String overview;
    @SerializedName("release_date")
    public String release_date;
    @SerializedName("genre_ids")
    public List<Integer> genre_ids;
    @SerializedName("id")
    public Integer id;
    @SerializedName("original_title")
    public String original_title;
    @SerializedName("original_language")
    public String original_language;
    @SerializedName("title")
    public String title;
    @SerializedName("backdrop_path")
    public String backdrop_path;
    @SerializedName("popularity")
    public Float popularity;
    @SerializedName("vote_count")
    public Integer vote_count;
    @SerializedName("video")
    public Boolean video;
    @SerializedName("vote_average")
    public Float vote_average;
}
