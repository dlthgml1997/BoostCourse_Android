package com.sohee.boostcourse_pjt.ui.movie.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.sohee.boostcourse_pjt.network.AppHelper;
import com.sohee.boostcourse_pjt.R;
import com.sohee.boostcourse_pjt.ui.movie.item.MovieItem;

public class MovieListFragment extends Fragment {

    private Button btnMoreInfo;
    private ImageView imgPoster;
    private TextView txtTitle;
    private TextView txtAge;
    private TextView txtDday;
    private TextView txtAdvanced;

    private MovieItem item;

    private onFragmentChangeListener onFragmentChangeListener;

    private String baseUrl = AppHelper.baseUrl;

    private int id;
    private String title;

    public MovieListFragment() {
        // Required empty public constructor
    }

    public static MovieListFragment newInstance(MovieItem movieitem) {

        Bundle bundle = new Bundle();
        bundle.putParcelable("movieItem", movieitem);

        MovieListFragment fragment = new MovieListFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        onFragmentChangeListener = (onFragmentChangeListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        onFragmentChangeListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = (View) inflater.inflate(R.layout.fragment_movie_list, container, false);

        btnMoreInfo = (Button) view.findViewById(R.id.btn_first_frag_more_info);
        imgPoster = (ImageView) view.findViewById(R.id.img_first_frag_poster);
        txtTitle = (TextView) view.findViewById(R.id.txt_first_frag_title);
        txtAdvanced = (TextView) view.findViewById(R.id.txt_first_frag_advance_rate);
        txtAge = (TextView) view.findViewById(R.id.txt_first_frag_age);
        txtDday = (TextView) view.findViewById(R.id.txt_first_frag_d_day);
        btnMoreInfo = (Button) view.findViewById(R.id.btn_first_frag_more_info);

        setOnBtnClickListener();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setContent();
    }


    public void setContent() {
        item = getArguments().getParcelable("movieItem");
        if (item != null) {
            Glide.with(getContext()).load(item.getImage()).into(imgPoster);
            txtTitle.setText(item.getTitle());
            txtAge.setText(" " + item.getGrade() + "세 관람가");
            txtDday.setText(item.getDate());
            txtAdvanced.setText(item.getReservation_rate() + "%");

            id = item.getId();
            title = item.getTitle();
        }
    }

    private void setOnBtnClickListener() {
        btnMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFragmentChangeListener.onFragmentChange(id,title);
            }
        });
    }

    public interface onFragmentChangeListener {
        public void onFragmentChange(int id, String title);
    }

}
