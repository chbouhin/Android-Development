package com.example.myandroiddevelopment.MovieList;

import java.io.Serializable;

public class MovieInfo implements Serializable {
    private String title;
    private String description;
    private String image;
    private Integer id;

    public MovieInfo(String title, String description, String image, Integer id) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.id = id;
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

    public Integer getId() {
        return id;
    }
}
