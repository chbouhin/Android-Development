package com.example.myandroiddevelopment.Views.Favorites;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myandroiddevelopment.Models.List.DiscoverMoviesModel;
import com.example.myandroiddevelopment.Models.List.ResultsDiscoverMovies;
import com.example.myandroiddevelopment.MovieList.MovieInfo;
import com.example.myandroiddevelopment.MovieList.MovieListAdapter;
import com.example.myandroiddevelopment.R;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {
    View _v;
    FavoritesController _controller = new FavoritesController();
    ArrayList<MovieInfo> movieInfoList;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _v = inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerView = _v.findViewById(R.id.recyclerViewMovie);
        movieInfoList = new ArrayList<>();
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
                setMovieInfo(discoverMoviesModel.results);
                setAdapter();
            }
        });
    }

    private void setAdapter()
    {
        MovieListAdapter movieListAdapter = new MovieListAdapter(movieInfoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieListAdapter);
    }

    private void setMovieInfo(List<ResultsDiscoverMovies> results)
    {
        for (int i = 0; i < results.size(); i++)
        {
            movieInfoList.add(new MovieInfo(results.get(i).title, results.get(i).overview, results.get(i).poster_path));
        }
    }
}