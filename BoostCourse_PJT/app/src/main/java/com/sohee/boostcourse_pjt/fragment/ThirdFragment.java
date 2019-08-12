package com.sohee.boostcourse_pjt.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.sohee.boostcourse_pjt.MovieListActivity;
import com.sohee.boostcourse_pjt.R;

public class ThirdFragment extends Fragment {

    private ImageView imgPoster;
    private TextView txtMovieTitle;
    private TextView txtAdvanced;
    private TextView txtAge;
    private TextView txtDday;
    private Button btnMoreInfo;
    public MovieListActivity movieListActivity;
    public MovieDetailFragment movieDetailFragment;
    public ActionBar actionBar;

    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        imgPoster = (ImageView) view.findViewById(R.id.img_first_frag_poster);
        txtMovieTitle = (TextView) view.findViewById(R.id.txt_first_frag_title);
        txtAdvanced = (TextView) view.findViewById(R.id.txt_first_frag_advance_rate);
        txtAge = (TextView) view.findViewById(R.id.txt_first_frag_age);
        txtDday = (TextView) view.findViewById(R.id.txt_first_frag_d_day);
        btnMoreInfo = (Button) view.findViewById(R.id.btn_first_frag_more_info);

        movieListActivity = new MovieListActivity();
        movieDetailFragment = new MovieDetailFragment();


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        changeMovie();
        setOnBtnClickListener();
    }

    private void setOnBtnClickListener() {
        btnMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();

                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fl_movie_list_act, movieDetailFragment).commit();

                actionBar = movieListActivity.getSupportActionBar();
                actionBar.setTitle("영화 상세");
            }
        });
    }

    private void changeMovie() {
        imgPoster.setImageResource(R.drawable.image3);
        txtMovieTitle.setText("3. 더 킹");
        txtAge.setText("19세 관람가");
        txtDday.setText("D-23");
        txtAdvanced.setText(" 43.5%");
    }

}
