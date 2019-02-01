package com.example.dicoding.listmovie;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private Context mContext;
    private ArrayList<MovieListItem> dataList;
    public static final String MOVIE_BASE_URL="https://image.tmdb.org/t/p/w185";

    public MovieListAdapter(ArrayList<MovieListItem> dataList) {
        this.dataList = dataList;
    }

    public MovieListAdapter(Context mContext, ArrayList<MovieListItem> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView judulMovie, deskripsiMovie;
        private ImageView imageMovie;
        private CardView cardView;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            judulMovie = itemView.findViewById(R.id.judul_movie);
            deskripsiMovie = itemView.findViewById(R.id.deskripsi_movie);
            imageMovie = itemView.findViewById(R.id.gambar_movie);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_item, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, final int position) {
        MovieListItem list = dataList.get(position);
        movieViewHolder.judulMovie.setText(list.getTitle());
        movieViewHolder.deskripsiMovie.setText(list.getOverview());
        Picasso.get().load(MOVIE_BASE_URL+list.getPosterPath()).into(movieViewHolder.imageMovie);
        movieViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent movieIntent = new Intent(mContext,DetailMovie.class);
                movieIntent.putExtra("detail",dataList.get(position));
                mContext.startActivity(movieIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null)? dataList.size() :0 ;
    }
}
