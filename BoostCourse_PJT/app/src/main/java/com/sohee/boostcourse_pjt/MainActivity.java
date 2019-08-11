package com.sohee.boostcourse_pjt;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.sohee.boostcourse_pjt.adapter.ReviewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static int requestCodeForWriteReview = 101;
    private ImageView imgThumbUp;
    private ImageView imgThumbDown;
    private TextView txtThumbUp;
    private TextView txtThumbDown;
    private TextView btnWriteReview;
    private Button btnReserve;
    private Button btnDetail;
    private int countUp = 0;
    private int countDown = 0;
    public ArrayList<ReviewItem> reviewItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setOnBtnClickListener();
        setAdapter();
    }

    private void setOnBtnClickListener() {
        imgThumbUp = (ImageView) findViewById(R.id.btn_main_act_thumb_up);
        imgThumbDown = (ImageView) findViewById(R.id.btn_main_act_thumb_down);
        btnWriteReview = (TextView) findViewById(R.id.txt_write);
        btnReserve = (Button) findViewById(R.id.btn_main_act_reserve);
        btnDetail = (Button) findViewById(R.id.btn_main_act_detail);
        txtThumbUp = (TextView) findViewById(R.id.txt_main_act_thumb_up);
        txtThumbDown = (TextView) findViewById(R.id.txt_main_act_thumb_down);

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
                Toast.makeText(getApplicationContext(), "예매하기 눌렸습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        btnWriteReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WriteReviewActivity.class);

                startActivityForResult(intent,requestCodeForWriteReview);
            }
        });

        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReviewDetailActivity.class);
                intent.putExtra("reviewItems", reviewItems);

                startActivity(intent);
            }
        });
    }

    private void setAdapter() {
        ListView reviewListView = (ListView) findViewById(R.id.lv_main_act_review);

        ReviewAdapter adapter = new ReviewAdapter();
        adapter.addItem(new ReviewItem("shfk***", "10분전", (float) 4, "재밌어요!"));
        adapter.addItem(new ReviewItem("dlth***", "15분전", (float) 3, "그저 그랬어요"));
        adapter.addItem(new ReviewItem("abcd***", "30분전", (float) 2, "지루했어요.."));
        adapter.addItem(new ReviewItem("leees***", "40분전", (float) 5, "인생 작품입니다."));
        reviewListView.setAdapter(adapter);

        reviewItems = new ArrayList<ReviewItem>();
        reviewItems = adapter.getItems();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case 101:
                    Toast.makeText(this,"리뷰 저장 눌림",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
