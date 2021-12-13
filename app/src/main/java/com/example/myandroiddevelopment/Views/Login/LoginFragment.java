package com.example.myandroiddevelopment.Views.Login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myandroiddevelopment.Models.Login.RequestTokenModel;
import com.example.myandroiddevelopment.Models.Login.SessionIDModel;
import com.example.myandroiddevelopment.R;
import com.example.myandroiddevelopment.RetrofitProvider;

public class LoginFragment extends Fragment {
    View _v;
    LoginController _controller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        _v = inflater.inflate(R.layout.fragment_login, container, false);
        _controller = new LoginController();
        InitOnControllerMutableChange();
        _controller.FetchRequestToken();
        return _v;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (_controller._requestToken.getValue() != null)
            _controller.FetchSessionID(_controller._requestToken.getValue());
    }

    private void InitOnControllerMutableChange()
    {
        _controller._requestToken.observe(getViewLifecycleOwner(), new Observer<RequestTokenModel>() {
            @Override
            public void onChanged(RequestTokenModel requestTokenModel) {
                if (requestTokenModel == null)
                    return;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.themoviedb.org/authenticate/" + requestTokenModel.request_token));
                startActivity(browserIntent);
            }
        });
        _controller._sessionID.observe(getViewLifecycleOwner(), new Observer<SessionIDModel>() {
            @Override
            public void onChanged(SessionIDModel sessionIDModel) {
                if (sessionIDModel == null)
                    return;
                RetrofitProvider.sessionID = sessionIDModel.session_id;
                TextView txt = _v.findViewById(R.id.displaySessionID);
                txt.setText(RetrofitProvider.sessionID);
                NavDirections action = LoginFragmentDirections.goAfterLogin();
                //NavDirections action = LoginFragmentDirections.goTestMovieInfo("597316");
                Navigation.findNavController(_v).navigate(action);
            }
        });
    }
}