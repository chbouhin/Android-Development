package com.example.myandroiddevelopment.Views.Search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

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
            }
        });
    }

    private void InitSearchView()
    {
        SearchView searchView = _v.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                _controller.FetchSearchMovies(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}