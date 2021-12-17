package com.example.myandroiddevelopment.Views.MovieDetail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myandroiddevelopment.Models.Account.MovieAccountStatesModel;
import com.example.myandroiddevelopment.Models.Movie.CompanyInfoModel;
import com.example.myandroiddevelopment.Models.Movie.MovieModel;
import com.example.myandroiddevelopment.R;

import java.io.InputStream;
import java.util.List;

public class MovieDetailFragment extends Fragment {
    View _v;
    MovieDetailController _controller = new MovieDetailController();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _v = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        InitControllerMutableChange();
        Integer movieID = MovieDetailFragmentArgs.fromBundle(getArguments()).getMovieID();
        _controller.FetchMovie(movieID);
        _controller.FetchMovieAccountStates(movieID);
        _controller.FetchAccount();
        InitBtnFav();
        InitBtnWatch();
        InitBtnRate();
        return _v;
    }

    private void InitBtnFav()
    {
        ImageButton btn = _v.findViewById(R.id.btn_addToFav);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _controller.AddToFavorite();
            }
        });
    }

    private void InitBtnWatch()
    {
        ImageButton btn = _v.findViewById(R.id.btn_addToWatchlist);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _controller.AddToWatchlist();
            }
        });
    }

    private void InitBtnRate()
    {
        Button btn = _v.findViewById(R.id.btn_rate);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText rateEditText = _v.findViewById(R.id.editTxt_rate);
                Float rate = Float.parseFloat(rateEditText.getText().toString());
                if (rate >= 0.5 && rate <= 10)
                    _controller.Rate(rate);
            }
        });
    }

    private void InitControllerMutableChange()
    {
        _controller._movie.observe(getViewLifecycleOwner(), new Observer<MovieModel>() {
            @Override
            public void onChanged(MovieModel movieModel) {
                ImageView poster = _v.findViewById(R.id.img_poster);
                TextView title = _v.findViewById(R.id.txt_title);
                TextView desc = _v.findViewById(R.id.txt_desc);
                TextView companiesName = _v.findViewById(R.id.txt_companies_name);
                TextView release = _v.findViewById(R.id.txt_release);
                new DownloadImageTask(poster).execute("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + movieModel.poster_path);
                title.setText(movieModel.original_title);
                desc.setText(movieModel.overview);
                companiesName.setText(companiesNameListToString(movieModel.production_companies));
                release.setText("Release date: " + movieModel.release_date);
            }
        });
        _controller._movieAccountStates.observe(getViewLifecycleOwner(), new Observer<MovieAccountStatesModel>() {
            @Override
            public void onChanged(MovieAccountStatesModel masm) {
                Log.d("ON MASM CHANGE", "id: " + masm.id);
                Log.d("ON MASM CHANGE", "favorite: " + masm.favorite);
                Log.d("ON MASM CHANGE", "rated: " + masm.rated.value);
                Log.d("ON MASM CHANGE", "watchlist: " + masm.watchlist);
                ImageButton addToFav = _v.findViewById(R.id.btn_addToFav);
                ImageButton addToWatchlist = _v.findViewById(R.id.btn_addToWatchlist);
                EditText rate = _v.findViewById(R.id.editTxt_rate);
                Button rate_btn = _v.findViewById(R.id.btn_rate);
                addToFav.setImageResource(masm.favorite ? R.drawable.ic_baseline_star_24 : R.drawable.ic_baseline_star_border_24);
                addToFav.setClickable(true);
                addToWatchlist.setImageResource(masm.watchlist ? R.drawable.ic_baseline_playlist_add_check_24 : R.drawable.ic_baseline_playlist_add_24);
                addToWatchlist.setClickable(true);
                rate.setText((masm.rated.value < 0.5) ? "0.5" : ((masm.rated.value > 10) ? "10" : masm.rated.value.toString()));
                rate_btn.setClickable(true);
            }
        });
    }

    private String companiesNameListToString(List<CompanyInfoModel> companiesList)
    {
        if (companiesList == null)
            return "";
        String res = "Production companies: ";
        boolean firstElem = true;
        for (CompanyInfoModel company: companiesList) {
            if (firstElem)
                firstElem = false;
            else
                res += ", ";
            res += company.name;
        }
        return (firstElem ? "" : res);
    }

    public static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}