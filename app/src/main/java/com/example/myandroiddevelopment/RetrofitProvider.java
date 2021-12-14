package com.example.myandroiddevelopment;

import com.example.myandroiddevelopment.Service.TMDBAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {
    static TMDBAPI _service = null;
    public static String apiKey = "e7ef6b8985f39448196af698af149821";
    public static String sessionID = null;

    public static void InitRetrofitInstance() {
        if (_service == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            _service = retrofit.create(TMDBAPI.class);
        }
    }

    public static TMDBAPI GetRetrofitInstance() {
        InitRetrofitInstance();
        return (_service);
    }
}
