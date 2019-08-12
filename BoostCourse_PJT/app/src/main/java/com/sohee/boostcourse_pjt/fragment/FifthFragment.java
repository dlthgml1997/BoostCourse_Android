package com.sohee.boostcourse_pjt.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.sohee.boostcourse_pjt.MovieListActivity;
import com.sohee.boostcourse_pjt.R;

public class FifthFragment extends Fragment {


    private ImageView imgPoster;
    private TextView txtMovieTitle;
    private TextView txtAdvanced;
    private TextView txtAge;
    private TextView txtDday;
    private Button btnMoreInfo;
    private MovieListActivity movieListActivity;

    public FifthFragment() {
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

        View view = inflater.inflate(R.layout.fragment_first, container, false);

        imgPoster = (ImageView) view.findViewById(R.id.img_first_frag_poster);
        txtMovieTitle = (TextView) view.findViewById(R.id.txt_first_frag_title);
        txtAdvanced = (TextView) view.findViewById(R.id.txt_first_frag_advance_rate);
        txtAge = (TextView) view.findViewById(R.id.txt_first_frag_age);
        txtDday = (TextView) view.findViewById(R.id.txt_first_frag_d_day);
        btnMoreInfo = (Button) view.findViewById(R.id.btn_first_frag_more_info);

        setOnBtnClickListener();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        changeMovie();
    }

    private void changeMovie() {
                imgPoster.setImageResource(R.drawable.image5);
                txtMovieTitle.setText("럭 키");
                txtAge.setText("15세 관람가");
                txtDday.setText("D-1");
                txtAdvanced.setText(" 8.8%");
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
