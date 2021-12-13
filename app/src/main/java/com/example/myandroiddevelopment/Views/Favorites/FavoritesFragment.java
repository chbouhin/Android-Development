package com.example.myandroiddevelopment.Views.Favorites;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myandroiddevelopment.Models.List.DiscoverMoviesModel;
import com.example.myandroiddevelopment.R;

public class FavoritesFragment extends Fragment {
    View _v;
    FavoritesController _controller = new FavoritesController();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _v = inflater.inflate(R.layout.fragment_favorites, container, false);
        InitOnControllerMutableChange();
        _controller.FetchFavoriteMovies();
        return _v;
    }

    private void InitOnControllerMutableChange()
    {
        _controller._result.observe(getViewLifecycleOwner(), new Observer<DiscoverMoviesModel>() {
            @Override
            public void onChanged(DiscoverMoviesModel discoverMoviesModel) {
                if (discoverMoviesModel == null)
                    return;
            }
        });
    }
}