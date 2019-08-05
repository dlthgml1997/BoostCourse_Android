package com.sohee.boostcourse_pjt;

import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView imgThumbUp;
    private ImageView imgThumbDown;
    private TextView txtThumbUp;
    private TextView txtThumbDown;
    private Button btnReserve;
    private Button btnDetail;
    private int countUp = 0;
    private int countDown = 0;

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
        btnReserve = (Button) findViewById(R.id.btn_main_act_reserve);
        btnDetail = (Button) findViewById(R.id.btn_main_act_detail);
        txtThumbUp = (TextView) findViewById(R.id.txt_main_act_thumb_up);
        txtThumbDown = (TextView) findViewById(R.id.txt_main_act_thumb_down);

        imgThumbUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View viewThumbUp) {
                if(viewThumbUp.isSelected()){
                    viewThumbUp.setSelected(false);
                    countUp--;
                    txtThumbUp.setText(String.valueOf(countUp));
                }else if(imgThumbDown.isSelected()){
                    imgThumbDown.setSelected(false);
                    imgThumbUp.setSelected(true);
                    countDown--;
                    countUp++;
                    txtThumbDown.setText(String.valueOf(countDown));
                    txtThumbUp.setText(String.valueOf(countUp));
                }else{
                    viewThumbUp.setSelected(true);
                    countUp++;
                    txtThumbUp.setText(String.valueOf(countUp));
                }
            }
        });

        imgThumbDown.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View viewThumbDown) {
                if(viewThumbDown.isSelected()){
                    viewThumbDown.setSelected(false);
                    countDown--;
                    txtThumbDown.setText(String.valueOf(countDown));
                }else if(imgThumbUp.isSelected()){
                    imgThumbDown.setSelected(true);
                    imgThumbUp.setSelected(false);
                    countDown++;
                    countUp--;
                    txtThumbDown.setText(String.valueOf(countDown));
                    txtThumbUp.setText(String.valueOf(countUp));
                }else{
                    viewThumbDown.setSelected(true);
                    countDown++;
                    txtThumbDown.setText(String.valueOf(countDown));
                }
            }
        });

        btnReserve.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"예매하기 눌렸습니다.",Toast.LENGTH_SHORT).show();
            }
        });

        btnDetail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"모두 보기 눌렸습니다.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter() {
        ListView reviewListView = (ListView) findViewById(R.id.lv_main_act_review);
        ReviewAdapter adapter = new ReviewAdapter();
        adapter.addItem(new ReviewItem("shfk***","10분전",(float)4,"재밌어요!"));
        adapter.addItem(new ReviewItem("dlth***","15분전",(float)3,"그저 그랬어요"));

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
            return view;
        }
    }


}
