package com.example.dicoding.listmovie;

public class MovieListItem {
    private String title;
    private String description;
    private int movieImage;

    public MovieListItem(String title, String description, int movieImage) {
        this.title = title;
        this.description = description;
        this.movieImage = movieImage;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getMovieImage() {
        return movieImage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMovieImage(int movieImage) {
        this.movieImage = movieImage;
    }
}
