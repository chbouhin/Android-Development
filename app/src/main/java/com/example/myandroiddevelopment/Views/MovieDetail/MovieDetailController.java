package com.example.myandroiddevelopment.Views.MovieDetail;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.myandroiddevelopment.Models.Account.AccountModel;
import com.example.myandroiddevelopment.Models.Account.MovieAccountStatesModel;
import com.example.myandroiddevelopment.Models.Account.MovieAccountStatesWithRatedBoolModel;
import com.example.myandroiddevelopment.Models.Post.AddFavoriteModel;
import com.example.myandroiddevelopment.Models.Movie.MovieModel;
import com.example.myandroiddevelopment.Models.Post.AddWatchlistModel;
import com.example.myandroiddevelopment.Models.Post.RateModel;
import com.example.myandroiddevelopment.Models.Post.StatusModel;
import com.example.myandroiddevelopment.RetrofitProvider;
import com.example.myandroiddevelopment.Service.TMDBAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailController {
    MutableLiveData<MovieModel> _movie = new MutableLiveData<MovieModel>();
    MutableLiveData<AccountModel> _account = new MutableLiveData<AccountModel>();
    MutableLiveData<StatusModel> _lastStatus = new MutableLiveData<StatusModel>();
    MutableLiveData<MovieAccountStatesModel> _movieAccountStates = new MutableLiveData<MovieAccountStatesModel>();
    TMDBAPI _service = RetrofitProvider.GetRetrofitInstance();

    public void FetchMovie(String movieID)
    {
        Call<MovieModel> call = _service.GetMovieInfo(movieID, RetrofitProvider.apiKey);
        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                _movie.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
            }
        });
    }

    public void FetchAccount()
    {
        Call<AccountModel> call = _service.GetAccount(RetrofitProvider.apiKey, RetrofitProvider.sessionID);
        call.enqueue(new Callback<AccountModel>() {
            @Override
            public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {
                _account.setValue(response.body());
            }

            @Override
            public void onFailure(Call<AccountModel> call, Throwable t) {
            }
        });
    }

    public void FetchMovieAccountStates(String movieID)
    {
        Call<MovieAccountStatesModel> call = _service.GetMovieAccountStates(movieID, RetrofitProvider.apiKey, RetrofitProvider.sessionID);
        call.enqueue(new Callback<MovieAccountStatesModel>() {
            @Override
            public void onResponse(Call<MovieAccountStatesModel> call, Response<MovieAccountStatesModel> response) {
                Log.d("FETCH MASM", response.toString());
                _movieAccountStates.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MovieAccountStatesModel> call, Throwable t) {
                Log.d("FETCH MASM", t.toString());
                Call<MovieAccountStatesWithRatedBoolModel> errCall = _service.GetMovieAccountStatesWithRatedBool(movieID, RetrofitProvider.apiKey, RetrofitProvider.sessionID);
                errCall.enqueue(new Callback<MovieAccountStatesWithRatedBoolModel>() {
                    @Override
                    public void onResponse(Call<MovieAccountStatesWithRatedBoolModel> call, Response<MovieAccountStatesWithRatedBoolModel> response) {
                        MovieAccountStatesWithRatedBoolModel base = response.body();
                        MovieAccountStatesModel tmp = new MovieAccountStatesModel();
                        tmp.id = base.id;
                        tmp.favorite = base.favorite;
                        tmp.watchlist = base.watchlist;
                        tmp.rated = new RateModel();
                        tmp.rated.value = 0f;
                        _movieAccountStates.setValue(tmp);
                    }

                    @Override
                    public void onFailure(Call<MovieAccountStatesWithRatedBoolModel> call, Throwable t) {
                    }
                });
            }
        });
    }

    public void AddToFavorite()
    {
        AddFavoriteModel elem = new AddFavoriteModel();
        elem.media_type = "movie";
        elem.media_id = _movie.getValue().id;
        elem.favorite = !_movieAccountStates.getValue().favorite;
        Call<StatusModel> call = _service.PostAddFavorite(_account.getValue().id, RetrofitProvider.apiKey, RetrofitProvider.sessionID, elem, "application/json;charset=utf-8");
        call.enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                Log.d("ADD TO FAV", response.toString());
                _lastStatus.setValue(response.body());
                MovieAccountStatesModel tmp = _movieAccountStates.getValue();
                tmp.favorite = !tmp.favorite;
                _movieAccountStates.setValue(tmp);
            }

            @Override
            public void onFailure(Call<StatusModel> call, Throwable t) {
            }
        });
    }

    public void AddToWatchlist()
    {
        AddWatchlistModel elem = new AddWatchlistModel();
        elem.media_type = "movie";
        elem.media_id = _movie.getValue().id;
        elem.watchlist = !_movieAccountStates.getValue().watchlist;
        Call<StatusModel> call = _service.PostAddWatchlist(_account.getValue().id, RetrofitProvider.apiKey, RetrofitProvider.sessionID, elem, "application/json;charset=utf-8");
        call.enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                Log.d("ADD TO WATCH", response.toString());
                _lastStatus.setValue(response.body());
                MovieAccountStatesModel tmp = _movieAccountStates.getValue();
                tmp.watchlist = !tmp.watchlist;
                _movieAccountStates.setValue(tmp);
            }

            @Override
            public void onFailure(Call<StatusModel> call, Throwable t) {
            }
        });
    }

    public void Rate(float rate)
    {
        RateModel rateModel = new RateModel();
        rateModel.value = rate;
        Call<StatusModel> call = _service.PostRateMovie(_movie.getValue().id, RetrofitProvider.apiKey, RetrofitProvider.sessionID, rateModel, "application/json;charset=utf-8");
        call.enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                _lastStatus.setValue(response.body());
                MovieAccountStatesModel tmp = _movieAccountStates.getValue();
                tmp.rated.value = rate;
                _movieAccountStates.setValue(tmp);
            }

            @Override
            public void onFailure(Call<StatusModel> call, Throwable t) {
            }
        });
    }
}
