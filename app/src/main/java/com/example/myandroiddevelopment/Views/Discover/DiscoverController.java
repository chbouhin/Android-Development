package com.example.myandroiddevelopment.Views.Discover;

import androidx.lifecycle.MutableLiveData;

import com.example.myandroiddevelopment.Models.List.DiscoverMoviesModel;
import com.example.myandroiddevelopment.RetrofitProvider;
import com.example.myandroiddevelopment.Service.TMDBAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverController {
    MutableLiveData<DiscoverMoviesModel> _result = new MutableLiveData<>();
    TMDBAPI _service = RetrofitProvider.GetRetrofitInstance();

    public void FetchDiscoverMovies()
    {
        Call<DiscoverMoviesModel> call = _service.GetAllMoviesInfo(RetrofitProvider.apiKey);
        call.enqueue(new Callback<DiscoverMoviesModel>() {
            @Override
            public void onResponse(Call<DiscoverMoviesModel> call, Response<DiscoverMoviesModel> response) {
                _result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DiscoverMoviesModel> call, Throwable t) {
            }
        });
    }
}
