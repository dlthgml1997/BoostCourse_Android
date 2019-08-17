package com.sohee.boostcourse_pjt.activity;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.sohee.boostcourse_pjt.R;
import com.sohee.boostcourse_pjt.adapter.ReviewAdapter;
import com.sohee.boostcourse_pjt.movie.fragment.MovieListFragment;
import com.sohee.boostcourse_pjt.model.ReviewItem;

import java.util.ArrayList;

public class ReviewDetailActivity extends AppCompatActivity {

    private TextView btnWriteReview;
    public ArrayList<ReviewItem> reviewItems;
    MovieListFragment movieListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_detail);

        getFragment();
        setOnBtnClickListener();
        setAdapter();
        setToolbar();
    }


    private void getFragment() {


    }

    private void setOnBtnClickListener() {
        btnWriteReview = (TextView) findViewById(R.id.txt_review_detail_act_write);

        btnWriteReview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WriteReviewActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setAdapter() {
        reviewItems = getIntent().getParcelableArrayListExtra("reviewItems");

        ListView reviewListView = (ListView) findViewById(R.id.lv_review_detail_act_review);

        ReviewAdapter adapter = new ReviewAdapter();

        adapter.setItems(reviewItems);

        reviewListView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
