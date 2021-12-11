package com.example.myandroiddevelopment.Service;

import com.example.myandroiddevelopment.Models.DiscoverMoviesModel;
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
    @GET("/3/discover/movie")
    Call<DiscoverMoviesModel> GetAllMoviesInfo(@Query("api_key") String apiKey);
    @GET("/3/account/{account_id}/favorite/movies")
    Call<DiscoverMoviesModel> GetFavoriteMoviesInfo(@Path("account_id") String movieID, @Query("api_key") String apiKey);
}
