package com.sohee.boostcourse_pjt.fragment;


import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sohee.boostcourse_pjt.MovieListActivity;
import com.sohee.boostcourse_pjt.R;

public class FirstFragment extends Fragment {

    private Button btnMoreInfo;
    private MovieListActivity movieListActivity;


    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        movieListActivity = (MovieListActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        movieListActivity = null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = (View) inflater.inflate(R.layout.fragment_first, container, false);

        btnMoreInfo = (Button) view.findViewById(R.id.btn_first_frag_more_info);

        setOnBtnClickListener();

        return view;
    }


    private void setOnBtnClickListener() {
        btnMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieListActivity.onFragmentChange(0);
                movieListActivity.changeActionBarTitleToMovieDetail();
            }
        });
    }

}
