package com.example.myandroiddevelopment.Views.Watchlist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myandroiddevelopment.R;

public class WatchlistFragment extends Fragment {
    View _v;
    WatchlistController _controller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _v = inflater.inflate(R.layout.fragment_watchlist, container, false);
        return _v;
    }
}