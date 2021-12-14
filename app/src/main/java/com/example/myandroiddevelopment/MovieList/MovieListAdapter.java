package com.example.myandroiddevelopment.MovieList;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myandroiddevelopment.R;
import com.example.myandroiddevelopment.Views.MovieDetail.MovieDetailFragment;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.RecyclerHolder> {
    private ArrayList<MovieInfo> moveInfoList;

    public MovieListAdapter(ArrayList<MovieInfo> moveInfoList)
    {
        this.moveInfoList = moveInfoList;
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;
        private ImageView image;

        public RecyclerHolder(final View view)
        {
            super(view);
            title = view.findViewById(R.id.titleMovie);
            description = view.findViewById(R.id.descriptionMovie);
            image = view.findViewById(R.id.imageMovie);
        }
    }

    @NonNull
    @Override
    public MovieListAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movies, parent, false);
        return new RecyclerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.RecyclerHolder holder, int position) {
        String title = moveInfoList.get(position).getTitle();
        String description = moveInfoList.get(position).getDescription();
        String image = moveInfoList.get(position).getImage();

        holder.title.setText(title);
        holder.description.setText(description);
        new MovieDetailFragment.DownloadImageTask(holder.image).execute("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + image);
    }

    @Override
    public int getItemCount() {
        return moveInfoList.size();
    }
}
