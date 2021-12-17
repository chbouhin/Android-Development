package com.example.myandroiddevelopment.MovieList;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myandroiddevelopment.R;
import com.example.myandroiddevelopment.Views.Discover.DiscoverFragment;
import com.example.myandroiddevelopment.Views.Discover.DiscoverFragmentDirections;
import com.example.myandroiddevelopment.Views.Favorites.FavoritesFragmentDirections;
import com.example.myandroiddevelopment.Views.Login.LoginFragmentDirections;
import com.example.myandroiddevelopment.Views.MovieDetail.MovieDetailFragment;
import com.example.myandroiddevelopment.Views.Search.SearchFragmentDirections;
import com.example.myandroiddevelopment.Views.Watchlist.WatchlistFragmentDirections;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.RecyclerHolder> {
    private ArrayList<MovieInfo> movieInfoList;
    private Scene scene;

    public MovieListAdapter(ArrayList<MovieInfo> movieInfoList, Scene scene)
    {
        this.movieInfoList = movieInfoList;
        this.scene = scene;
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;
        private ImageView image;
        private Button button;

        public RecyclerHolder(final View view)
        {
            super(view);
            title = view.findViewById(R.id.titleMovie);
            description = view.findViewById(R.id.descriptionMovie);
            image = view.findViewById(R.id.imageMovie);
            button = view.findViewById(R.id.buttonMovie);
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
        String title = movieInfoList.get(position).getTitle();
        String description = movieInfoList.get(position).getDescription();
        String image = movieInfoList.get(position).getImage();

        holder.title.setText(title);
        holder.description.setText(description);
        new MovieDetailFragment.DownloadImageTask(holder.image).execute("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + image);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = null;
                switch(scene) {
                    case DISCOVER:
                        action = DiscoverFragmentDirections.discoverToMovieInfo(movieInfoList.get(holder.getAdapterPosition()).getId());
                        break;
                    case FAVORITES:
                        action = FavoritesFragmentDirections.favoritesToMovieInfo(movieInfoList.get(holder.getAdapterPosition()).getId());
                        break;
                    case SEARCH:
                        action = SearchFragmentDirections.searchToMovieInfo(movieInfoList.get(holder.getAdapterPosition()).getId());
                        break;
                    case WATCHLIST:
                        action = WatchlistFragmentDirections.watchlistToMovieInfo(movieInfoList.get(holder.getAdapterPosition()).getId());
                        break;
                    default:
                        break;
                }
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieInfoList.size();
    }
}

