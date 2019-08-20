package com.sohee.boostcourse_pjt.ui.movie.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.sohee.boostcourse_pjt.*;
import com.sohee.boostcourse_pjt.network.AppHelper;
import com.sohee.boostcourse_pjt.ui.movie.get.getMovieDetailResponse;
import com.sohee.boostcourse_pjt.ui.movie.item.MovieDetailItem;
import com.sohee.boostcourse_pjt.ui.review.adapter.ReviewAdapter;
import com.sohee.boostcourse_pjt.ui.review.get.getReviewListResponse;
import com.sohee.boostcourse_pjt.ui.review.model.ReviewItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MovieDetailFragment extends Fragment {

    private onReplaceFragmentListener mListener;

    private ImageView imgThumbUp;
    private ImageView imgThumbDown;
    private TextView txtThumbUp;
    private TextView txtThumbDown;
    private TextView btnWriteReview;
    private Button btnReserve;
    private Button btnDetail;
    private ImageView imgPoster;
    private TextView txtMovieTitle;
    private TextView txtOpenDate;
    private ImageView imgGrade;
    private TextView txtgenreInfo;
    private TextView txtReservationRate;
    private RatingBar ratingBar;
    private TextView txtRating;
    private TextView txtAudience;
    private TextView txtSynopsis;
    private TextView txtDirector;
    private TextView txtActor;

    private View view;

    private int countUp = 0;
    private int countDown = 0;

    private ArrayList<ReviewItem> reviewItems;

    private int id;
    private MovieDetailItem item;

    public MovieDetailFragment() {

    }

    public static MovieDetailFragment newInstance(int id) {

        Bundle bundle = new Bundle();
        bundle.putInt("id", id);

        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.setArguments(bundle);

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

        imgThumbUp = (ImageView) view.findViewById(R.id.btn_main_act_thumb_up);
        imgThumbDown = (ImageView) view.findViewById(R.id.btn_main_act_thumb_down);
        btnWriteReview = (TextView) view.findViewById(R.id.txt_write);
        btnReserve = (Button) view.findViewById(R.id.btn_main_act_reserve);
        btnDetail = (Button) view.findViewById(R.id.btn_main_act_detail);
        txtThumbUp = (TextView) view.findViewById(R.id.txt_main_act_thumb_up);
        txtThumbDown = (TextView) view.findViewById(R.id.txt_main_act_thumb_down);

        imgPoster = view.findViewById(R.id.img_poster_main);
        txtMovieTitle = view.findViewById(R.id.txt_movie_detail_movietitle);
        txtOpenDate = view.findViewById(R.id.txt_opendate_main);
        imgGrade = view.findViewById(R.id.img_movie_detail_frag_grade);
        txtgenreInfo = view.findViewById(R.id.txt_movieinfo_main);
        txtReservationRate = view.findViewById(R.id.txt_movie_detail_frag_reservation_rate);
        ratingBar = view.findViewById(R.id.rb_movie_detail_frag_rating);
        txtRating = view.findViewById(R.id.txt_movie_detail_frag_rating);
        txtAudience = view.findViewById(R.id.txt_movie_detail_frag_audience);
        txtSynopsis = view.findViewById(R.id.txt_movie_detail_frag_synopsis);
        txtDirector = view.findViewById(R.id.txt_movie_detail_frag_director);
        txtActor = view.findViewById(R.id.txt_movie_detail_frag_actor);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        id = getArguments().getInt("id");

        getRequestQueue();
        getMovieDetailResponse(id);
        getReviewItemLimitResponse(id);

        setOnBtnClickListener();
    }

    private void getRequestQueue() {
        if (AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getContext());
        }
    }

    private void getMovieDetailResponse(int id) {
        this.id = id;

        StringRequest request = new StringRequest(
                Request.Method.GET,
                AppHelper.baseUrl + "movie/readMovie?id=" + id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("getMovieDetailResponse", "응답 -> " + response);

                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("getMovieDetailResponse", "에러 -> " + error);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
        Log.d("getMovieDetailResponse", "요청 보냄.");
    }

    private void processResponse(String response) {
        Gson gson = new Gson();
        getMovieDetailResponse getMovieDetailResponse = gson.fromJson(response, getMovieDetailResponse.class);

        Log.d("getMovieListResponse", getMovieDetailResponse.result.toString());
        if (getMovieDetailResponse != null) {
            item = getMovieDetailResponse.result.get(0);
            setContent(item);
        }
    }

    private void setContent(MovieDetailItem item) {
        countUp = item.getLike();
        countDown = item.getDislike();
        txtThumbUp.setText(item.getLike() + "");
        txtThumbDown.setText(item.getDislike() + "");
        Glide.with(getContext()).load(item.getThumb()).into(imgPoster);
        txtMovieTitle.setText(item.getTitle());
        txtOpenDate.setText(item.getDate() + " 개봉");
        switch (item.getGrade()) {
            default:

            case 12:
                Glide.with(getContext()).load(R.drawable.ic_12).into(imgGrade);
                break;

            case 15:
                Glide.with(getContext()).load(R.drawable.ic_15).into(imgGrade);
                break;

            case 19:
                Glide.with(getContext()).load(R.drawable.ic_19).into(imgGrade);
                break;
        }
        txtgenreInfo.setText(item.getGenre() + " / " + item.getDuration());
        txtReservationRate.setText(item.getReservation_grade() + "위 " + item.getReservation_rate() + "%");
        ratingBar.setRating(item.getReviewer_rating() / 2);
        txtRating.setText(item.getReviewer_rating() + "");
        txtAudience.setText(item.getAudience() + "");
        txtSynopsis.setText(item.getSynopsis());
        txtDirector.setText(item.getDirector());
        txtActor.setText(item.getActor());
    }

    private void setOnBtnClickListener() {

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

    private void getReviewItemLimitResponse(int id){
        this.id = id;

        StringRequest request = new StringRequest(
                Request.Method.GET,
                AppHelper.baseUrl + "movie/readCommentList?id=" + id+"&limit=2",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("getReviewLimitRes", "응답 -> " + response);

                        processReviewResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("getReviewLimitRes", "에러 -> " + error);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
        Log.d("getReviewLimitRes", "요청 보냄.");
    }

    private void processReviewResponse(String response) {
        Gson gson = new Gson();
        getReviewListResponse getMovieListResponse = gson.fromJson(response, getReviewListResponse.class);

        Log.d("getReviewLimitRes", getMovieListResponse.result.toString());
        if (getMovieListResponse != null) {
            reviewItems = getMovieListResponse.result;
            setAdapter(reviewItems);
        }
    }

    private void setAdapter(ArrayList<ReviewItem> reviewItems) {
        ListView reviewListView = (ListView) view.findViewById(R.id.lv_main_act_review);

        ReviewAdapter adapter = new ReviewAdapter();
        for (int i = 0; i < reviewItems.size(); i++) {
            adapter.addItem(reviewItems.get(i));
        }

        reviewListView.setAdapter(adapter);
    }

    public interface onReplaceFragmentListener {
        void changeActionBarTitleToMovieDetail();

        void setDrawer();

        void setToolbar();

        void startWriteReviewAct();

        void startReviewDetailAct(ArrayList<ReviewItem> reviewItems);
    }

}
