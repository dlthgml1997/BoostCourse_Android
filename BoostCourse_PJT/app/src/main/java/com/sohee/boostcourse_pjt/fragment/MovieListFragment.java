package com.sohee.boostcourse_pjt.fragment;


import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sohee.boostcourse_pjt.MovieListActivity;
import com.sohee.boostcourse_pjt.R;
import com.sohee.boostcourse_pjt.model.MovieItem;

public class MovieListFragment extends Fragment {

    private Button btnMoreInfo;
    private TextView title;
    private MovieListActivity movieListActivity;
    private MovieItem item;


    public MovieListFragment() {
        // Required empty public constructor
    }

    public static MovieListFragment getInstance(MovieItem movieitem) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("movieItem", movieitem);
        MovieListFragment fragment = new MovieListFragment();
        fragment.setArguments(bundle);
        return fragment;
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
        title = (TextView) view.findViewById(R.id.txt_first_frag_title);

        setOnBtnClickListener();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        item = getArguments();

    }

    private void setOnBtnClickListener() {
        btnMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieListActivity.onFragmentChange(0);
            }
        });
    }

}
