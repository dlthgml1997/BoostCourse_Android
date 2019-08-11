package com.sohee.boostcourse_pjt.fragment;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sohee.boostcourse_pjt.R;

public class FirstFragment extends Fragment {

    private ImageView imgPoster;
    private TextView txtMovieTitle;
    private TextView txtAdvanced;
    private TextView txtAge;
    private TextView txtDday;
    private View view;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         view = (View) inflater.inflate(R.layout.fragment_first, container, false);
//
//        imgPoster = (ImageView) view.findViewById(R.id.img_first_frag_poster);
//        txtMovieTitle = (TextView) view.findViewById(R.id.txt_first_frag_title);
//        txtAdvanced = (TextView) view.findViewById(R.id.txt_first_frag_advance_rate);
//        txtAge = (TextView) view.findViewById(R.id.txt_first_frag_age);
//        txtDday = (TextView) view.findViewById(R.id.txt_first_frag_d_day);

        return view;
    }


    public void changeMovie(int index,ImageView image,TextView txt) {

        switch (index) {
//            case 1:
//
//                imgPoster.setImageResource(R.drawable.image1);
//                txtMovieTitle.setText("1. 군 도");
//                txtAge.setText("15세 관람가");
//                txtDday.setText("D-2");
//                txtAdvanced.setText("61.6%");
//                break;

            case 2:
                image.setImageResource(R.drawable.image2);
                txt.setText("2. 공 조");
                txtAge.setText("12세 관람가");
                txtDday.setText("D-34");
                txtAdvanced.setText("59.6%");
                break;

//            case 3:
//                imgPoster.setImageResource(R.drawable.image3);
//                txtMovieTitle.setText("3. 더 킹");
//                txtAge.setText("19세 관람가");
//                txtDday.setText("D-23");
//                txtAdvanced.setText(" 43.5%");
//                break;

//            case 4:
//                imgPoster.setImageResource(R.drawable.image4);
//                txtMovieTitle.setText("4. 레지턴트 이블");
//                txtAge.setText("15세 관람가");
//                txtDday.setText("D-9");
//                txtAdvanced.setText(" 12.1%");
//                break;

//            case 5:
//                imgPoster.setImageResource(R.drawable.image5);
//                txtMovieTitle.setText("럭 키");
//                txtAge.setText("15세 관람가");
//                txtDday.setText("D-1");
//                txtAdvanced.setText("8.8%");
//                break;

            default:
                break;
        }
    }
}
