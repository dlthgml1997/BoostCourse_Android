package com.sohee.boostcourse_pjt;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.sohee.boostcourse_pjt.adapter.ReviewAdapter;

import java.util.ArrayList;

public class ReviewDetailActivity extends AppCompatActivity {

    private TextView btnWriteReview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_detail);
        setOnBtnClickListener();
        setAdapter();
        setToolbar();
    }

    private void setOnBtnClickListener() {
        btnWriteReview = (TextView) findViewById(R.id.txt_review_detail_act_write);

        btnWriteReview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),WriteReviewActivity.class);
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
        ListView reviewListView = (ListView) findViewById(R.id.lv_review_detail_act_review);
        ReviewAdapter adapter = new ReviewAdapter();
        reviewListView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
