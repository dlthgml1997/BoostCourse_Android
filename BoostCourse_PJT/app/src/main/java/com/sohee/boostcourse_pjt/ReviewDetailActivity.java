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
        adapter.addItem(new ReviewItem("shfk***","10분전",(float)4,"재밌어요!"));
        adapter.addItem(new ReviewItem("dlth***","15분전",(float)3,"그저 그랬어요"));
        adapter.addItem(new ReviewItem("abcd***","30분전",(float)2,"지루했어요.."));
        adapter.addItem(new ReviewItem("leees***","40분전",(float)5,"인생 작품입니다."));

        reviewListView.setAdapter(adapter);
    }

    class ReviewAdapter extends BaseAdapter {
        ArrayList<ReviewItem> items = new ArrayList<ReviewItem>();
        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(ReviewItem item){
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            ReviewItemView view = new ReviewItemView(getApplicationContext());

            ReviewItem item = items.get(position);
            view.setId(item.getId());
            view.setTime(item.getTime());
            view.setRating(item.getRating());
            view.setReview(item.getReview());
            return view;
        }
    }
}
