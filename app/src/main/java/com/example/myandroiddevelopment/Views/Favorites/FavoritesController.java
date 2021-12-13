package com.example.myandroiddevelopment.Views.Favorites;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.myandroiddevelopment.Models.DiscoverMoviesModel;
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
        Call<DiscoverMoviesModel> call = _service.GetFavoriteMoviesInfo("ACCOUNT ID", RetrofitProvider.apiKey, RetrofitProvider.sessionID);
        call.enqueue(new Callback<DiscoverMoviesModel>() {
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
}
