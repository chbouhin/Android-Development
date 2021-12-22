package com.example.myandroiddevelopment.Views.Search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.myandroiddevelopment.Models.List.DiscoverMoviesModel;
import com.example.myandroiddevelopment.Models.Movie.MovieModel;
import com.example.myandroiddevelopment.MovieList.MovieInfo;
import com.example.myandroiddevelopment.MovieList.MovieListAdapter;
import com.example.myandroiddevelopment.MovieList.Scene;
import com.example.myandroiddevelopment.R;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    View _v;
    SearchController _controller = new SearchController();
    ArrayList<MovieInfo> movieInfoList;
    RecyclerView recyclerView;
    Integer totalPages = 1;
    Boolean canScroll = true;
    String querySave = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _v = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = _v.findViewById(R.id.recyclerViewMovie);
        movieInfoList = new ArrayList<>();
        InitOnControllerMutableChange();
        SetAdapter();
        InitSearchView();
        return _v;
    }

    private void InitOnControllerMutableChange()
    {
        _controller._result.observe(getViewLifecycleOwner(), new Observer<DiscoverMoviesModel>() {
            @Override
            public void onChanged(DiscoverMoviesModel discoverMoviesModel) {
                if (discoverMoviesModel == null)
                    return;
                canScroll = true;
                totalPages = discoverMoviesModel.total_pages;
                SetMovieInfo(discoverMoviesModel.results);
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });
    }

    private void InitSearchView()
    {
        SearchView searchView = _v.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieInfoList.clear();
                searchView.clearFocus();
                querySave = query;
                _controller.FetchSearchMovies(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void SetAdapter()
    {
        MovieListAdapter movieListAdapter = new MovieListAdapter(movieInfoList, Scene.SEARCH);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieListAdapter);
    }

    private void SetMovieInfo(List<MovieModel> results)
    {
        for (int i = 0; i < results.size(); i++)
            movieInfoList.add(new MovieInfo(results.get(i).title, results.get(i).overview, results.get(i).poster_path, results.get(i).id));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE && canScroll) {
                    _controller.FetchNextPage(querySave, totalPages);
                    canScroll = false;
                }
            }
        });
    }
}