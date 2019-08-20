package com.sohee.boostcourse_pjt.ui.review.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.sohee.boostcourse_pjt.R;
import org.w3c.dom.Text;

public class ReviewItemView extends LinearLayout {
    TextView txtId;
    TextView txtTime;
    RatingBar txtRating;
    TextView txtReview;
    TextView txtRecommend;

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
