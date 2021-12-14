package com.example.myandroiddevelopment;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

public class MainApp extends Application {
    // optional
    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitProvider.InitRetrofitInstance();
    }

    // optional
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    // optional
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
