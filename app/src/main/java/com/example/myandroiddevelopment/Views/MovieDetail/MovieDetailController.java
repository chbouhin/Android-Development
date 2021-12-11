package com.example.myandroiddevelopment.Views.MovieDetail;

import android.provider.ContactsContract;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.myandroiddevelopment.Models.AccountModel;
import com.example.myandroiddevelopment.Models.AddFavoriteModel;
import com.example.myandroiddevelopment.Models.MovieModel;
import com.example.myandroiddevelopment.Models.RateModel;
import com.example.myandroiddevelopment.Models.StatusModel;
import com.example.myandroiddevelopment.RetrofitProvider;
import com.example.myandroiddevelopment.Service.TMDBAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailController {
    MutableLiveData<MovieModel> _movie = new MutableLiveData<MovieModel>();
    MutableLiveData<AccountModel> _account = new MutableLiveData<AccountModel>();
    MutableLiveData<StatusModel> _lastStatus = new MutableLiveData<StatusModel>();
    MutableLiveData<Boolean> _isAlreadyFav = new MutableLiveData<Boolean>(false);
    TMDBAPI _service = RetrofitProvider.getRetrofitInstance();

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

    public void AddToFavorite()
    {
        AddFavoriteModel elem = new AddFavoriteModel();
        elem.media_type = "movie";
        elem.media_id = _movie.getValue().id;
        elem.favorite = (_isAlreadyFav.getValue()) ? false : true;
        Call<StatusModel> call = _service.PostAddFavorite(_account.getValue().id, RetrofitProvider.apiKey, RetrofitProvider.sessionID, elem, "application/json;charset=utf-8");
        call.enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                _lastStatus.setValue(response.body());
                Log.d("ADD TO FAV", response.body().status_code + " : " + response.body().status_message);
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
                Log.d("RATE MOVIE", response.body().status_code + " : " + response.body().status_message);
            }

            @Override
            public void onFailure(Call<StatusModel> call, Throwable t) {
            }
        });
    }
}
