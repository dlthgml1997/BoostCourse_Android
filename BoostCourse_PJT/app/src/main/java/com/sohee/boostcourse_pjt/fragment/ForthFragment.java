package com.sohee.boostcourse_pjt.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.sohee.boostcourse_pjt.R;

public class ForthFragment extends Fragment {

    private ImageView imgPoster;
    private TextView txtMovieTitle;
    private TextView txtAdvanced;
    private TextView txtAge;
    private TextView txtDday;

    public ForthFragment() {
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

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        changeMovie();
    }

    private void changeMovie() {
                imgPoster.setImageResource(R.drawable.image4);
                txtMovieTitle.setText("4. 레지턴트 이블");
                txtAge.setText("15세 관람가");
                txtDday.setText("D-9");
                txtAdvanced.setText(" 12.1%");
    }
}
