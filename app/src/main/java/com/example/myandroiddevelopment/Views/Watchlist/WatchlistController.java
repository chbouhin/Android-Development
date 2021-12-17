package com.example.myandroiddevelopment.Views.Watchlist;

import androidx.lifecycle.MutableLiveData;

import com.example.myandroiddevelopment.Models.Account.AccountModel;
import com.example.myandroiddevelopment.Models.List.DiscoverMoviesModel;
import com.example.myandroiddevelopment.RetrofitProvider;
import com.example.myandroiddevelopment.Service.TMDBAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WatchlistController {
    MutableLiveData<DiscoverMoviesModel> _result = new MutableLiveData<>();
    TMDBAPI _service = RetrofitProvider.GetRetrofitInstance();
    Integer actualPage = 1;

    public void FetchWatchlistMovies(Integer page)
    {
        Call<AccountModel> call = _service.GetAccount(RetrofitProvider.apiKey, RetrofitProvider.sessionID);
        call.enqueue(new Callback<AccountModel>() {
            @Override
            public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {
                Call<DiscoverMoviesModel> watchCall = _service.GetWatchlistMoviesInfo(response.body().id, RetrofitProvider.apiKey, RetrofitProvider.sessionID, page + "");
                watchCall.enqueue(new Callback<DiscoverMoviesModel>() {
                    @Override
                    public void onResponse(Call<DiscoverMoviesModel> call, Response<DiscoverMoviesModel> response) {
                        _result.setValue(response.body());
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

    public void FetchWatchlistMovies()
    {
        FetchWatchlistMovies(1);
    }

    public void FetchNextPage(Integer totalPages)
    {
        if (actualPage < totalPages) {
            actualPage += 1;
            FetchWatchlistMovies(actualPage);
        }
    }
}
