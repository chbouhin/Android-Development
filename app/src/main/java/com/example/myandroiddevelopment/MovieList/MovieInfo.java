package com.example.myandroiddevelopment.MovieList;

import java.io.Serializable;

public class MovieInfo implements Serializable {
    private String title;
    private String description;
    private String image;

    public MovieInfo(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
}
