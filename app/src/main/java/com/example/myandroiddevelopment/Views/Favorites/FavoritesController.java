package com.example.myandroiddevelopment.Views.Favorites;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.myandroiddevelopment.Models.Account.AccountModel;
import com.example.myandroiddevelopment.Models.List.DiscoverMoviesModel;
import com.example.myandroiddevelopment.RetrofitProvider;
import com.example.myandroiddevelopment.Service.TMDBAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritesController {
    MutableLiveData<DiscoverMoviesModel> _result = new MutableLiveData<>();
    TMDBAPI _service = RetrofitProvider.getRetrofitInstance();

    public void FetchFavoriteMovies()
    {
        Call<AccountModel> call = _service.GetAccount(RetrofitProvider.apiKey, RetrofitProvider.sessionID);
        call.enqueue(new Callback<AccountModel>() {
            @Override
            public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {
                Call<DiscoverMoviesModel> favCall = _service.GetFavoriteMoviesInfo(response.body().id, RetrofitProvider.apiKey, RetrofitProvider.sessionID);
                favCall.enqueue(new Callback<DiscoverMoviesModel>() {
                    @Override
                    public void onResponse(Call<DiscoverMoviesModel> call, Response<DiscoverMoviesModel> response) {
                        _result.setValue(response.body());
                        Log.d("result", _result.getValue().total_results + "");
                    }

                    @Override
                    public void onFailure(Call<DiscoverMoviesModel> call, Throwable t) {
                    }
                });
            }

            @Override
            public void onFailure(Call<AccountModel> call, Throwable t) {
            }
        });
    }
}
