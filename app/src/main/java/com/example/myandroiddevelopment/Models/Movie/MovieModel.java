package com.example.myandroiddevelopment.Models.Movie;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieModel {
    @SerializedName("adult")
    public Boolean adult;
    @SerializedName("backdrop_path")
    public String backdrop_path;
    @SerializedName("belongs_to_collection")
    public CollectionInfoModel belongs_to_collection;
    @SerializedName("budget")
    public Integer budget;
    @SerializedName("genres")
    public List<GenreInfoModel> genres;
    @SerializedName("homepage")
    public String homepage;
    @SerializedName("id")
    public Integer id;
    @SerializedName("imdb_id")
    public String imdb_id;
    @SerializedName("original_language")
    public String original_language;
    @SerializedName("original_title")
    public String original_title;
    @SerializedName("overview")
    public String overview;
    @SerializedName("popularity")
    public Float popularity;
    @SerializedName("poster_path")
    public String poster_path;
    @SerializedName("production_companies")
    public List<CompanyInfoModel> production_companies;
    @SerializedName("production_countries")
    public List<CountryInfoModel> production_countries;
    @SerializedName("release_date")
    public String release_date;
    @SerializedName("revenue")
    public Integer revenue;
    @SerializedName("runtime")
    public Integer runtime;
    @SerializedName("spoken_languages")
    public List<LanguageInfoModel> spoken_languages;
    @SerializedName("status")
    public String status;
    @SerializedName("tagline")
    public String tagline;
    @SerializedName("title")
    public String title;
    @SerializedName("video")
    public Boolean video;
    @SerializedName("vote_average")
    public Float vote_average;
    @SerializedName("vote_count")
    public Integer vote_count;
}
