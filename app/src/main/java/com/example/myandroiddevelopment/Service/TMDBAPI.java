package com.example.myandroiddevelopment.Service;

import com.example.myandroiddevelopment.Models.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TMDBAPI {
    @GET("/movie/{movieID}")
    Call<MovieModel> GetMovieInfo(@Path("movieID") String movieID);
}
