package com.example.dicoding.listmovie;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    Button upComming,nowPlaying;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("List Movie");
        upComming = view.findViewById(R.id.to_up_comming);
        upComming.setOnClickListener(this);
        nowPlaying = view.findViewById(R.id.to_now_playing);
        nowPlaying.setOnClickListener(this);
    }
    FragmentManager mFragmentManager;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.to_up_comming:
                UpComming mUpComming = new UpComming();
                mFragmentManager = getFragmentManager();
                if (mFragmentManager != null){
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.main_frame,mUpComming,UpComming.class
                            .getSimpleName());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                break;
            case R.id.to_now_playing:
                NowPlaying mNowPlaying = new NowPlaying();
                mFragmentManager = getFragmentManager();
                if (mFragmentManager != null){
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.main_frame,mNowPlaying,NowPlaying.class
                            .getSimpleName());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                break;
        }
    }
}
