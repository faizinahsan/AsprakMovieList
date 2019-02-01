package com.example.dicoding.listmovie;


import android.content.Intent;
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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;

import utilities.NetworkUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpComming extends Fragment{
    private RecyclerView recyclerView;
    private MovieListAdapter adapter;
    private ArrayList<MovieListItem> movieListItemArrayList;
    String upCommingUrl;
    ProgressBar mPorgressBar;
    public UpComming() {
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
//        addData();
        getActivity().setTitle("Up Comming");
        mPorgressBar = view.findViewById(R.id.indeterminateBar);
        recyclerView = view.findViewById(R.id.recycler_up_comming);
        new RetriveUpCommingData().execute();
//        adapter = new MovieListAdapter(getContext(),movieListItemArrayList);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
    }



//    private void addData() {
//        movieListItemArrayList = new ArrayList<>();
//        movieListItemArrayList.add(new MovieListItem("Aquaman","Dia adalah Aquaman",R.drawable
//                .ic_launcher_background));
//        movieListItemArrayList.add(new MovieListItem("Batman","Im Batman, Hes Batman, Everyone " +
//                "Batman",R
//                .drawable
//                .ic_launcher_background));
//        movieListItemArrayList.add(new MovieListItem("Man Of Steel","Is He really made of steel??",R
//                .drawable
//                .ic_launcher_background));
//    }


    private class RetriveUpCommingData extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            upCommingUrl = "https://api.themoviedb.org/3/movie/upcoming?api_key=0421fe7c9fd6d2048fb64bfca56e3819&language=en-US";
            movieListItemArrayList = new ArrayList<>();

            try {
                if (NetworkUtils.networkStatus(getActivity())){
                    movieListItemArrayList = NetworkUtils.fetchData(upCommingUrl);
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
            mPorgressBar.setVisibility(View.GONE);
            adapter = new MovieListAdapter(getContext(),movieListItemArrayList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }
}
