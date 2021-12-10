package com.example.myandroiddevelopment.Views.Discover;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myandroiddevelopment.R;

public class DiscoverFragment extends Fragment {
    View _v;
    DiscoverController _controller = new DiscoverController();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _v = inflater.inflate(R.layout.fragment_discover, container, false);
        return _v;
    }
}