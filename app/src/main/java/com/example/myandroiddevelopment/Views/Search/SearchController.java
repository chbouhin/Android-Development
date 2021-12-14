package com.example.myandroiddevelopment.Views.Search;

import androidx.lifecycle.MutableLiveData;

import com.example.myandroiddevelopment.Models.List.DiscoverMoviesModel;
import com.example.myandroiddevelopment.RetrofitProvider;
import com.example.myandroiddevelopment.Service.TMDBAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchController {
    MutableLiveData<DiscoverMoviesModel> _result = new MutableLiveData<>();
    TMDBAPI _service = RetrofitProvider.GetRetrofitInstance();

    public void FetchSearchMovies(String query)
    {
        Call<DiscoverMoviesModel> call = _service.GetSearchMoviesInfo(RetrofitProvider.apiKey, query);
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
