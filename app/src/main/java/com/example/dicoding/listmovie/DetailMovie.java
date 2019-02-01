package com.example.dicoding.listmovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailMovie extends AppCompatActivity {
    private static final String TAG = DetailMovie.class.getSimpleName();
    public static final String MOVIE_BASE_URL="https://image.tmdb.org/t/p/w185";
    TextView titleMovie, descMovie,popularityOfMovie,voteOfMovie,dateMovie;
    ImageView imageMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        Intent intent = getIntent();
        MovieListItem movieListItem = (MovieListItem) intent.getSerializableExtra("detail");

        titleMovie = findViewById(R.id.title_place_holder);
        descMovie = findViewById(R.id.desc_place_holder);
        imageMovie = findViewById(R.id.image_place_holder);
        popularityOfMovie = findViewById(R.id.popularity_place_holder);
        voteOfMovie = findViewById(R.id.vote_place_holder);
        dateMovie = findViewById(R.id.date_place_holder);

        titleMovie.setText(movieListItem.getTitle());
        descMovie.setText(movieListItem.getOverview());
        Picasso.get().load(MOVIE_BASE_URL+movieListItem.getPosterPath()).into(imageMovie);
        popularityOfMovie.setText("Popularity: "+movieListItem.getPopularity());
        voteOfMovie.setText("Vote: "+ movieListItem.getVoteCount());
        dateMovie.setText("Release Date: "+movieListItem.getReleaseDate());
    }
}
