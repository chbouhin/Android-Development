package com.example.myandroiddevelopment.Views.Login;

import androidx.lifecycle.MutableLiveData;

import com.example.myandroiddevelopment.Models.Login.RequestTokenModel;
import com.example.myandroiddevelopment.Models.Login.SessionIDModel;
import com.example.myandroiddevelopment.RetrofitProvider;
import com.example.myandroiddevelopment.Service.TMDBAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginController {
    MutableLiveData<RequestTokenModel> _requestToken = new MutableLiveData<RequestTokenModel>();
    MutableLiveData<SessionIDModel> _sessionID = new MutableLiveData<SessionIDModel>();
    TMDBAPI _service = RetrofitProvider.GetRetrofitInstance();

    public void FetchRequestToken()
    {
        Call<RequestTokenModel> call = _service.GetAuthToken(RetrofitProvider.apiKey);
        call.enqueue(new Callback<RequestTokenModel>() {
            @Override
            public void onResponse(Call<RequestTokenModel> call, Response<RequestTokenModel> response) {
                _requestToken.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RequestTokenModel> call, Throwable t) {
            }
        });
    }

    public void FetchSessionID(RequestTokenModel requestToken)
    {
        Call<SessionIDModel> call = _service.PostNewSession(RetrofitProvider.apiKey, requestToken);
        call.enqueue(new Callback<SessionIDModel>() {
            @Override
            public void onResponse(Call<SessionIDModel> call, Response<SessionIDModel> response) {
                _sessionID.setValue(response.body());
            }

            @Override
            public void onFailure(Call<SessionIDModel> call, Throwable t) {
            }
        });
    }
}
