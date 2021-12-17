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
    Integer actualPage = 1;

    public void FetchDiscoverMovies(Integer page)
    {
        Call<DiscoverMoviesModel> call = _service.GetAllMoviesInfo(RetrofitProvider.apiKey, page + "");
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

    public void FetchDiscoverMovies()
    {
        FetchDiscoverMovies(1);
    }

    public void FetchNextPage(Integer totalPages)
    {
        if (actualPage < totalPages) {
            actualPage += 1;
            FetchDiscoverMovies(actualPage);
        }
    }
}
