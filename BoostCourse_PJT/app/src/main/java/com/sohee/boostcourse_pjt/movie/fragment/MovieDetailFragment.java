package com.sohee.boostcourse_pjt.movie.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.sohee.boostcourse_pjt.*;
import com.sohee.boostcourse_pjt.movie.MainActivity;
import com.sohee.boostcourse_pjt.review.ReviewDetailActivity;
import com.sohee.boostcourse_pjt.review.WriteReviewActivity;
import com.sohee.boostcourse_pjt.review.adapter.ReviewAdapter;
import com.sohee.boostcourse_pjt.review.model.ReviewItem;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailFragment extends Fragment {

    private onReplaceFragmentListener mListener;

    private ImageView imgThumbUp;
    private ImageView imgThumbDown;
    private TextView txtThumbUp;
    private TextView txtThumbDown;
    private TextView btnWriteReview;
    private Button btnReserve;
    private Button btnDetail;

    private View view;

    private int countUp = 0;
    private int countDown = 0;

    private ArrayList<ReviewItem> reviewItems;

    public MovieDetailFragment(){

    }

    public static MovieDetailFragment newInstance(){
        MovieDetailFragment fragment = new MovieDetailFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mListener = (onReplaceFragmentListener) context;

        mListener.setToolbar();
        mListener.setDrawer();
        mListener.changeActionBarTitleToMovieDetail();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = (View) inflater.inflate(R.layout.fragment_movie_detail, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        setOnBtnClickListener();
        setAdapter();
    }

    private void setOnBtnClickListener() {
        imgThumbUp = (ImageView) view.findViewById(R.id.btn_main_act_thumb_up);
        imgThumbDown = (ImageView) view.findViewById(R.id.btn_main_act_thumb_down);
        btnWriteReview = (TextView) view.findViewById(R.id.txt_write);
        btnReserve = (Button) view.findViewById(R.id.btn_main_act_reserve);
        btnDetail = (Button) view.findViewById(R.id.btn_main_act_detail);
        txtThumbUp = (TextView) view.findViewById(R.id.txt_main_act_thumb_up);
        txtThumbDown = (TextView) view.findViewById(R.id.txt_main_act_thumb_down);

        imgThumbUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewThumbUp) {
                if (viewThumbUp.isSelected()) {
                    viewThumbUp.setSelected(false);
                    countUp--;
                    txtThumbUp.setText(String.valueOf(countUp));
                } else if (imgThumbDown.isSelected()) {
                    imgThumbDown.setSelected(false);
                    imgThumbUp.setSelected(true);
                    countDown--;
                    countUp++;
                    txtThumbDown.setText(String.valueOf(countDown));
                    txtThumbUp.setText(String.valueOf(countUp));
                } else {
                    viewThumbUp.setSelected(true);
                    countUp++;
                    txtThumbUp.setText(String.valueOf(countUp));
                }
            }
        });

        imgThumbDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewThumbDown) {
                if (viewThumbDown.isSelected()) {
                    viewThumbDown.setSelected(false);
                    countDown--;
                    txtThumbDown.setText(String.valueOf(countDown));
                } else if (imgThumbUp.isSelected()) {
                    imgThumbDown.setSelected(true);
                    imgThumbUp.setSelected(false);
                    countDown++;
                    countUp--;
                    txtThumbDown.setText(String.valueOf(countDown));
                    txtThumbUp.setText(String.valueOf(countUp));
                } else {
                    viewThumbDown.setSelected(true);
                    countDown++;
                    txtThumbDown.setText(String.valueOf(countDown));
                }
            }
        });

        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "예매하기 눌렸습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        btnWriteReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.startWriteReviewAct();
            }
        });

        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.startReviewDetailAct(reviewItems);
            }
        });
    }

    private void setAdapter() {
        ListView reviewListView = (ListView) view.findViewById(R.id.lv_main_act_review);

        ReviewAdapter adapter = new ReviewAdapter();
        adapter.addItem(new ReviewItem("shfk***", "10분전", (float) 4, "재밌어요!"));
        adapter.addItem(new ReviewItem("dlth***", "15분전", (float) 3, "그저 그랬어요"));
        adapter.addItem(new ReviewItem("abcd***", "30분전", (float) 2, "지루했어요.."));
        adapter.addItem(new ReviewItem("leees***", "40분전", (float) 5, "인생 작품입니다."));
        reviewListView.setAdapter(adapter);

        reviewItems = new ArrayList<ReviewItem>();
        reviewItems = adapter.getItems();
    }

    public interface onReplaceFragmentListener {
        void changeActionBarTitleToMovieDetail();

        void setDrawer();

        void setToolbar();

        void startWriteReviewAct();

        void startReviewDetailAct(ArrayList<ReviewItem> reviewItems);
    }

}
