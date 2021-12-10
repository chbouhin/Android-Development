package com.example.myandroiddevelopment.Views.Search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myandroiddevelopment.R;

public class SearchFragment extends Fragment {
    View _v;
    SearchController _controller = new SearchController();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _v = inflater.inflate(R.layout.fragment_search, container, false);
        return _v;
    }
}