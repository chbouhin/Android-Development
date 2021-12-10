package com.example.myandroiddevelopment.Service;

import com.example.myandroiddevelopment.Models.MovieModel;
import com.example.myandroiddevelopment.Models.RequestTokenModel;
import com.example.myandroiddevelopment.Models.SessionIDModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDBAPI {
    @GET("/3/authentication/token/new")
    Call<RequestTokenModel> GetAuthToken(@Query("api_key") String apiKey);
    @POST("/3/authentication/session/new")
    Call<SessionIDModel> PostNewSession(@Query("api_key") String apiKey, @Body RequestTokenModel requestToken);
    @GET("/3/movie/{movieID}")
    Call<MovieModel> GetMovieInfo(@Path("movieID") String movieID, @Query("api_key") String apiKey);
}
