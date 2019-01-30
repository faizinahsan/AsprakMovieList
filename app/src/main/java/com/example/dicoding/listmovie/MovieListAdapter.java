package com.example.dicoding.listmovie;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private ArrayList<MovieListItem> dataList;

    public MovieListAdapter(ArrayList<MovieListItem> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_item, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {
        movieViewHolder.judulMovie.setText(dataList.get(position).getTitle());
        movieViewHolder.deskripsiMovie.setText(dataList.get(position).getDescription());
        movieViewHolder.imageMovie.setImageResource(dataList.get(position).getMovieImage());
    }

    @Override
    public int getItemCount() {
        return (dataList != null)? dataList.size() :0 ;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView judulMovie, deskripsiMovie;
        private ImageView imageMovie;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            judulMovie = itemView.findViewById(R.id.judul_movie);
            deskripsiMovie = itemView.findViewById(R.id.deskripsi_movie);
            imageMovie = itemView.findViewById(R.id.gambar_movie);
        }
    }
}
