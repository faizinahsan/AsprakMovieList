package com.example.dicoding.listmovie;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpComming extends Fragment {

    private RecyclerView recyclerView;
    private MovieListAdapter adapter;
    private ArrayList<MovieListItem> movieListItemArrayList;

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
        addData();
        recyclerView = view.findViewById(R.id.recycler_up_comming);
        adapter = new MovieListAdapter(movieListItemArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void addData() {
        movieListItemArrayList = new ArrayList<>();
        movieListItemArrayList.add(new MovieListItem("Aquaman","Dia adalah Aquaman",R.drawable
                .ic_launcher_background));
        movieListItemArrayList.add(new MovieListItem("Batman","Im Batman, Hes Batman, Everyone " +
                "Batman",R
                .drawable
                .ic_launcher_background));
        movieListItemArrayList.add(new MovieListItem("Man Of Steel","Is He really made of steel??",R
                .drawable
                .ic_launcher_background));
    }
}
