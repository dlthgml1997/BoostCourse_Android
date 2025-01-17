package com.sohee.boostcourse_pjt.ui.movie.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.sohee.boostcourse_pjt.db.DBHelper;
import com.sohee.boostcourse_pjt.ui.movie.GalleryDetailActivity;
import com.sohee.boostcourse_pjt.ui.movie.adapter.GalleryRVAdapter;
import com.sohee.boostcourse_pjt.ui.movie.get.GetMovieDetailResponse;
import com.sohee.boostcourse_pjt.ui.movie.item.GalleryItem;
import com.sohee.boostcourse_pjt.ui.movie.item.MovieDetailItem;
import com.sohee.boostcourse_pjt.ui.review.ReviewDetailActivity;
import com.sohee.boostcourse_pjt.ui.review.WriteReviewActivity;
import com.sohee.boostcourse_pjt.ui.review.adapter.ReviewAdapter;
import com.sohee.boostcourse_pjt.ui.review.get.GetReviewListResponse;
import com.sohee.boostcourse_pjt.ui.review.get.GetStatusResponse;
import com.sohee.boostcourse_pjt.ui.review.item.ReviewItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sohee.boostcourse_pjt.network.NetworkStatus.hasInternetConnection;

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

    private ReviewAdapter adapter;

    private View view;

    private int countUp = 0;
    private int countDown = 0;

    private ArrayList<ReviewItem> reviewItems;

    private int id;
    private String title;
    private MovieDetailItem item;
    private ArrayList<MovieDetailItem> DetailItems;

    private RecyclerView galleryRV;

    private GalleryRVAdapter galleryRVAdapter;

    ArrayList<GalleryItem> galleryItems;


    public MovieDetailFragment() {

    }

    public static MovieDetailFragment newInstance(int id, String title) {

        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("title", title);

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

        galleryRV = view.findViewById(R.id.rv_frag_movie_detail_gallery);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        id = getArguments().getInt("id");
        title = getArguments().getString("title");

        getRequestQueue();
        getMovieDetailResponse(id, title);
        getReviewItemResponse(id);

        setOnBtnClickListener();
    }

    private void getLikeResponse(int id, String like) {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                AppHelper.baseUrl + "movie/increaseLikeDisLike?id=" + id + "&likeyn=" + like,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("LikeResponse", "응답 -> " + response);

                        processLikeResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("LikeResponse", "에러 -> " + error);
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
        Log.d("LikeResponse", "요청 보냄.");
    }

    private void getDisLikeResponse(int id, String dislike) {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                AppHelper.baseUrl + "movie/increaseLikeDisLike?id=" + id + "&dislikeyn=" + dislike,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("LikeResponse", "응답 -> " + response);

                        processLikeResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("LikeResponse", "에러 -> " + error);
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
        Log.d("LikeResponse", "요청 보냄.");
    }

    private void processLikeResponse(String response) {
        Gson gson = new Gson();
        GetStatusResponse getStatusResponse = gson.fromJson(response, GetStatusResponse.class);

        if (getStatusResponse.getCode() == 200) {
            Log.d("LikeResponse", getStatusResponse.getMessage());
        }
    }

    private void getRequestQueue() {
        if (AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getContext());
        }
    }

    private void getMovieDetailResponse(int id, String title) {
        this.id = id;
        this.title = title;
        Log.d("DBHelper", title);

        if (hasInternetConnection(getContext())) {
            StringRequest request = new StringRequest(
                    Request.Method.GET,
                    AppHelper.baseUrl + "movie/readMovie?id=" + id,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("GetMovieDetailResponse", "응답 -> " + response);

                            processResponse(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("GetMovieDetailResponse", "에러 -> " + error);
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
            Log.d("GetMovieDetailResponse", "요청 보냄.");
        } else {
            DetailItems = (ArrayList<MovieDetailItem>) DBHelper.selectTable("inline");

            if (DetailItems != null) {
                for (int i = 0; i < DetailItems.size(); i++) {
                    if (DetailItems.get(i).getTitle().equals(title)) {
                        Log.d("DBHelper", DetailItems.get(i).getTitle());
                        item = DetailItems.get(i);
                        setContent(item);
                    }
                }
            }
        }
    }

    private void processResponse(String response) {
        Gson gson = new Gson();
        GetMovieDetailResponse getMovieDetailResponse = gson.fromJson(response, GetMovieDetailResponse.class);

        if (hasInternetConnection(getContext())) {
            if (getMovieDetailResponse != null) {
                Log.d("GetMovieListResponse", getMovieDetailResponse.result.toString());
                item = getMovieDetailResponse.result.get(0);
                setContent(item);

                setGalleryItem(item);

                DBHelper.insertInlineData(item);
            }
        }
    }

    private void setGalleryItem(MovieDetailItem item) {
        String photos = item.getPhotos();
        String videos = item.getVideos();

        try {
            List<String> photoList = Arrays.asList(photos.split(","));
            List<String> videoLink = Arrays.asList(videos.split(","));

            galleryItems = new ArrayList<>();

            for (int i = 0; i < photoList.size(); i++) {
                galleryItems.add(new GalleryItem(false, photoList.get(i)));
            }

            for (int i = 0; i < videoLink.size(); i++) {
                galleryItems.add(new GalleryItem(true, videoLink.get(i)));
            }

            setGalleryAdapter();

        }catch (Exception e){ e.printStackTrace(); }
    }

    private void setGalleryAdapter() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        galleryRV.setLayoutManager(layoutManager);

        galleryRVAdapter = new GalleryRVAdapter(getContext(), new GalleryRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(GalleryRVAdapter.ViewHolder holder, View view, int position) {
                GalleryItem item = (GalleryItem) galleryRVAdapter.getItem(position);
                setGalleryAdapterClickListener(item);
            }
        });

        galleryRV.setAdapter(galleryRVAdapter);

        galleryRVAdapter.addItems(galleryItems);

    }

    private void setGalleryAdapterClickListener(GalleryItem item) {
                if(item.isVideo()){
                    String link = item.getLink();

                    Intent intent  = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                    startActivity(intent);

                }else{
                    Intent intent = new Intent(getActivity(), GalleryDetailActivity.class);
                    intent.putExtra("photo",item.getLink());

                    startActivity(intent);
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
                if (viewThumbUp.isSelected()) {
                    getLikeResponse(id, "Y");
                } else {
                    getLikeResponse(id, "N");
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

                if (viewThumbDown.isSelected()) {
                    getDisLikeResponse(id, "Y");
                } else {
                    getDisLikeResponse(id, "N");
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
                if (hasInternetConnection(getContext())) {
                    startWriteReviewAct(item);
                } else {
                    Toast.makeText(getContext(), "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startReviewDetailAct(item);
            }
        });
    }

    private void getReviewItemResponse(int id) {
        this.id = id;

        if (hasInternetConnection(getContext())) {
            StringRequest request = new StringRequest(
                    Request.Method.GET,
                    AppHelper.baseUrl + "movie/readCommentList?id=" + id + "&limit=2",
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
        } else {
            reviewItems = (ArrayList<ReviewItem>) DBHelper.selectTable("review");
            if (reviewItems != null) {
                for (int i = 0; i < reviewItems.size(); i++) {
                    if (reviewItems.get(i).getMovieTitle().equals(title)) {
                        setAdapter(reviewItems);
                    }
                }
            }
        }
    }

    private void processReviewResponse(String response) {
        Gson gson = new Gson();
        GetReviewListResponse getReviewListResponse = gson.fromJson(response, GetReviewListResponse.class);
        if (hasInternetConnection(getContext())) {

            if (getReviewListResponse != null) {
                Log.d("getReviewLimitRes", getReviewListResponse.result.toString());

                reviewItems = getReviewListResponse.result;
                for (int i = 0; i < getReviewListResponse.result.size(); i++) {
                    reviewItems.get(i).setMovieTitle(title);
                }
                setAdapter(reviewItems);

                DBHelper.insertReviewData(reviewItems);
            }
        }
    }

    private void setAdapter(final ArrayList<ReviewItem> reviewItems) {
        ListView reviewListView = (ListView) view.findViewById(R.id.lv_main_act_review);
        adapter = new ReviewAdapter();
        for (int i = 0; i < reviewItems.size(); i++) {
            adapter.addItem(reviewItems.get(i));
        }

        reviewListView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    public void startWriteReviewAct(MovieDetailItem item) {
        if (hasInternetConnection(getContext())) {
            Intent intent = new Intent(getContext(), WriteReviewActivity.class);
            intent.putExtra("MovieDetailItem", item);

            startActivityForResult(intent, 101);
        } else {
            Toast.makeText(getActivity(), "인터넷을 연결해주세요.", Toast.LENGTH_SHORT).show();
        }
    }

    public void startReviewDetailAct(MovieDetailItem item) {
        Intent intent = new Intent(getContext(), ReviewDetailActivity.class);
        intent.putExtra("MovieDetailItem", item);
        intent.putExtra("title", title);

        startActivityForResult(intent, 102);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 101 || requestCode == 102) {
                getReviewItemResponse(id);
            }
        }
    }

    public interface onReplaceFragmentListener {
        void changeActionBarTitleToMovieDetail();

        void setDrawer();

        void setToolbar();
    }

}
