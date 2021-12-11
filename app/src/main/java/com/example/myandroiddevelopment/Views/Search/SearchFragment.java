package com.example.myandroiddevelopment.Views.Search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myandroiddevelopment.Models.DiscoverMoviesModel;
import com.example.myandroiddevelopment.R;

public class SearchFragment extends Fragment {
    View _v;
    SearchController _controller = new SearchController();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _v = inflater.inflate(R.layout.fragment_search, container, false);
        InitOnControllerMutableChange();
        _controller.FetchSearchMovies();
        return _v;
    }

    private void InitOnControllerMutableChange()
    {
        _controller._requestToken.observe(getViewLifecycleOwner(), new Observer<DiscoverMoviesModel>() {
            @Override
            public void onChanged(DiscoverMoviesModel discoverMoviesModel) {
                if (discoverMoviesModel == null)
                    return;
            }
        });
    }
}