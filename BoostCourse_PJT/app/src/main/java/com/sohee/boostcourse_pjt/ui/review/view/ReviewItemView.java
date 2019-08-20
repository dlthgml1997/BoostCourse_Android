package com.sohee.boostcourse_pjt.ui.review.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.sohee.boostcourse_pjt.R;
import com.sohee.boostcourse_pjt.network.AppHelper;
import com.sohee.boostcourse_pjt.ui.review.get.getReviewListResponse;
import com.sohee.boostcourse_pjt.ui.review.get.getStatusResponse;

import java.util.HashMap;
import java.util.Map;

public class ReviewItemView extends LinearLayout {
    TextView txtId;
    TextView txtTime;
    RatingBar txtRating;
    TextView txtReview;
    TextView txtRecommend;
    TextView btnRecommend;


    public ReviewItemView(Context context) {
        super(context);
        init(context);
    }

    public ReviewItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item_review, this, true);

        txtId = (TextView) findViewById(R.id.txt_review_item_id);
        txtTime = (TextView) findViewById(R.id.txt_review_item_time);
        txtRating = (RatingBar) findViewById(R.id.rb_review_item_rating);
        txtReview = (TextView) findViewById(R.id.txt_review_item_review);
        txtRecommend = (TextView) findViewById(R.id.txt_review_item_recommend);
        btnRecommend =(TextView) findViewById(R.id.txt_review_item_recommend_btn);

    }

    public TextView getBtnRecommend() {
        return btnRecommend;
    }

    public void setRecommend(String recommend) {
        txtRecommend.setText(recommend);
    }

    public void setId(String id) {
        txtId.setText(id);
    }

    public void setTime(String time) {
        txtTime.setText(time);
    }

    public void setRating(Float rating) {
        txtRating.setRating(rating);
    }

    public void setReview(String review) {
        txtReview.setText(review);
    }


}
