package com.example.dicoding.listmovie;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import utilities.NetworkUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlaying extends Fragment {
    private RecyclerView recyclerView;
    private MovieListAdapter adapter;
    private ArrayList<MovieListItem> movieListItemArrayList;
    String nowPlayingUrl;
    ProgressBar mProgressBar;
    public NowPlaying() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_up_comming, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Now Playing");
        mProgressBar = view.findViewById(R.id.indeterminateBar);
        recyclerView = view.findViewById(R.id.recycler_up_comming);
        new RetriveNowPlayingData().execute();
    }

    private class RetriveNowPlayingData extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            nowPlayingUrl = "https://api.themoviedb" +
                    ".org/3/movie/now_playing?api_key=0421fe7c9fd6d2048fb64bfca56e3819&language=en-US";
            movieListItemArrayList = new ArrayList<>();

            try {
                if (NetworkUtils.networkStatus(getActivity())){
                    movieListItemArrayList = NetworkUtils.fetchData(nowPlayingUrl);
                }else{
                    Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT)
                            .show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("doInBackground","total Data :"+movieListItemArrayList.size());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mProgressBar.setVisibility(View.GONE);
            adapter = new MovieListAdapter(getContext(),movieListItemArrayList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }
}
