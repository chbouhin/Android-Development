package com.example.myandroiddevelopment.Views.MovieDetail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myandroiddevelopment.R;

public class MovieDetailFragment extends Fragment {
    View _v;
    MovieDetailController _controller = new MovieDetailController();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _v = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        return _v;
    }
}