package com.example.myandroiddevelopment.Views.MovieDetail;

import androidx.lifecycle.MutableLiveData;

import com.example.myandroiddevelopment.Models.MovieModel;
import com.example.myandroiddevelopment.RetrofitProvider;
import com.example.myandroiddevelopment.Service.TMDBAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailController {
    MutableLiveData<MovieModel> _movie = new MutableLiveData<MovieModel>();
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
}
