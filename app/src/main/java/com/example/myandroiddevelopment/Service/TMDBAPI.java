package com.example.myandroiddevelopment.Service;

import com.example.myandroiddevelopment.Models.Account.AccountModel;
import com.example.myandroiddevelopment.Models.Account.MovieAccountStatesModel;
import com.example.myandroiddevelopment.Models.Account.MovieAccountStatesWithRatedBoolModel;
import com.example.myandroiddevelopment.Models.Post.AddFavoriteModel;
import com.example.myandroiddevelopment.Models.List.DiscoverMoviesModel;
import com.example.myandroiddevelopment.Models.Movie.MovieModel;
import com.example.myandroiddevelopment.Models.Post.AddWatchlistModel;
import com.example.myandroiddevelopment.Models.Post.RateModel;
import com.example.myandroiddevelopment.Models.Login.RequestTokenModel;
import com.example.myandroiddevelopment.Models.Login.SessionIDModel;
import com.example.myandroiddevelopment.Models.Post.StatusModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
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
    @GET("/3/movie/{movieID}/account_states")
    Call<MovieAccountStatesModel> GetMovieAccountStates(@Path("movieID") String movieID, @Query("api_key") String apiKey, @Query("session_id") String sessionID);
    @GET("/3/movie/{movieID}/account_states")
    Call<MovieAccountStatesWithRatedBoolModel> GetMovieAccountStatesWithRatedBool(@Path("movieID") String movieID, @Query("api_key") String apiKey, @Query("session_id") String sessionID);
    @GET("/3/discover/movie")
    Call<DiscoverMoviesModel> GetAllMoviesInfo(@Query("api_key") String apiKey, @Query("page") String page);
    @GET("/3/account/{accountId}/favorite/movies")
    Call<DiscoverMoviesModel> GetFavoriteMoviesInfo(@Path("accountId") Integer accountId, @Query("api_key") String apiKey, @Query("session_id") String sessionID, @Query("page") String page);
    @GET("/3/account/{accountId}/watchlist/movies")
    Call<DiscoverMoviesModel> GetWatchlistMoviesInfo(@Path("accountId") Integer accountId, @Query("api_key") String apiKey, @Query("session_id") String sessionID, @Query("page") String page);
    @GET("/3/account")
    Call<AccountModel> GetAccount(@Query("api_key") String apiKey, @Query("session_id") String sessionID);
    @POST("/3/account/{accountID}/favorite")
    Call<StatusModel> PostAddFavorite(@Path("accountID") Integer accountID, @Query("api_key") String apiKey, @Query("session_id") String sessionID, @Body AddFavoriteModel body, @Header("Content-Type") String contentType);
    @POST("/3/account/{accountID}/watchlist")
    Call<StatusModel> PostAddWatchlist(@Path("accountID") Integer accountID, @Query("api_key") String apiKey, @Query("session_id") String sessionID, @Body AddWatchlistModel body, @Header("Content-Type") String contentType);
    @POST("/3/movie/{movieID}/rating")
    Call<StatusModel> PostRateMovie(@Path("movieID") Integer accountID, @Query("api_key") String apiKey, @Query("session_id") String sessionID, @Body RateModel value, @Header("Content-Type") String contentType);
    @GET("/3/search/movie")
    Call<DiscoverMoviesModel> GetSearchMoviesInfo(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") String page);
}
