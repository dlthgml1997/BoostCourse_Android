package com.sohee.boostcourse_pjt.ui.review;

import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.sohee.boostcourse_pjt.R;
import com.sohee.boostcourse_pjt.network.AppHelper;
import com.sohee.boostcourse_pjt.ui.movie.item.MovieDetailItem;
import com.sohee.boostcourse_pjt.ui.review.adapter.ReviewAdapter;
import com.sohee.boostcourse_pjt.ui.review.get.getReviewListResponse;
import com.sohee.boostcourse_pjt.ui.review.item.ReviewItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReviewDetailActivity extends AppCompatActivity {

    private TextView btnWriteReview;
    private ArrayList<ReviewItem> reviewItems;
    private MovieDetailItem item;

    private TextView txtTitle;
    private ImageView imgGrade;
    private RatingBar ratingBarAudience;
    private TextView txtRateAudience;
    private TextView txtAudienceCount;

    private ReviewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_detail);

        item = getIntent().getParcelableExtra("MovieDetailItem");

        txtTitle = findViewById(R.id.txt_review_detail_title);
        imgGrade = findViewById(R.id.img_review_detail_act_grade);
        ratingBarAudience = findViewById(R.id.rb_review_detail_act_ratingbar);
        txtRateAudience = findViewById(R.id.txt_review_detail_rating);
        txtAudienceCount = findViewById(R.id.txt_review_detail_act_audience);

        setContent(item);

        getRequestQueue();
        getReviewItemLimitResponse(item.getId());

        setOnBtnClickListener();
        setToolbar();
    }

    private void setContent(MovieDetailItem item) {
        txtTitle.setText(item.getTitle());
        switch (item.getGrade()) {
            default:

            case 12:
                imgGrade.setImageResource(R.drawable.ic_12);
                break;

            case 15:
                imgGrade.setImageResource(R.drawable.ic_15);
                break;

            case 19:
                imgGrade.setImageResource(R.drawable.ic_19);
                break;
        }
        ratingBarAudience.setRating(item.getAudience_rating() / 2);
        txtRateAudience.setText(item.getAudience_rating() + "");
        txtAudienceCount.setText("(" + item.getAudience() + "명 참여)");
    }

    private void getRequestQueue() {
        if (AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    private void getReviewItemLimitResponse(int id) {

        StringRequest request = new StringRequest(
                Request.Method.GET,
                AppHelper.baseUrl + "movie/readCommentList?id=" + id + "&startIndex=0&length=20",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("getReviewRes", "응답 -> " + response);

                        processReviewResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("getReviewRes", "에러 -> " + error);
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
        Log.d("getReviewRes", "요청 보냄.");
    }

    private void processReviewResponse(String response) {
        Gson gson = new Gson();
        getReviewListResponse getMovieListResponse = gson.fromJson(response, getReviewListResponse.class);

        Log.d("getReviewRes", getMovieListResponse.result.toString());
        if (getMovieListResponse != null) {
            reviewItems = getMovieListResponse.result;
            setAdapter(reviewItems);
        }
    }

    private void setAdapter(ArrayList<ReviewItem> reviewItems) {

        ListView reviewListView = (ListView) findViewById(R.id.lv_review_detail_act_review);

        adapter = new ReviewAdapter();

        this.reviewItems = reviewItems;

        adapter.setItems(reviewItems);

        reviewListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setOnBtnClickListener() {
        btnWriteReview = (TextView) findViewById(R.id.txt_review_detail_act_write);

        btnWriteReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WriteReviewActivity.class);
                intent.putExtra("MovieDetailItem", item);
                startActivityForResult(intent, 1004);
            }
        });
    }

    private void setToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                setResult(RESULT_OK);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_OK);
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1004) {
                getReviewItemLimitResponse(item.getId());
            }
        }
    }

}
