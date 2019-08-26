package com.sohee.boostcourse_pjt.ui.review;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.*;
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
import com.sohee.boostcourse_pjt.ui.review.get.GetStatusResponse;
import com.sohee.boostcourse_pjt.ui.review.item.WriteReviewItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WriteReviewActivity extends AppCompatActivity {

    private Button btnCancel;
    private Button btnSave;
    private TextView txtTitle;
    private EditText etContents;
    private RatingBar ratingBar;
    private ImageView imgGrade;
    private String writer = "Sohee";
    private String time;

    private WriteReviewItem item = new WriteReviewItem();
    private MovieDetailItem movieItem;
    private int id;
    private String title;
    private int grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        etContents = (EditText) findViewById(R.id.edt_write_review_act_context);
        ratingBar = (RatingBar) findViewById(R.id.rb_write_review_act_rating);
        txtTitle = (TextView) findViewById(R.id.txt_write_review_act_movie_title);
        imgGrade = (ImageView) findViewById(R.id.img_write_review_act_grade);

        movieItem = getIntent().getParcelableExtra("MovieDetailItem");
        id = movieItem.getId();

        setContent();

        getRequestQueue();

        setOnBtnClickListener();
    }

    private void setContent() {
        title = movieItem.getTitle();
        txtTitle.setText(title);

        grade = movieItem.getGrade();
        switch (grade) {
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
    }

    private String getTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String time = sdf.format(date);
        return time;
    }

    private void getRequestQueue() {
        if (AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    private void getCreateCommentResponse(int id) {
        time = getTime();

        this.id = id;
        item.setContents(etContents.getText().toString());
        item.setWriter(writer);
        item.setRating(ratingBar.getRating());
        item.setTime(time);

        StringRequest request = new StringRequest(
                Request.Method.GET,
                AppHelper.baseUrl + "movie/createComment?id=" +
                        id + "&writer=" + item.getWriter() + "&time=" + item.getTime() +
                        "&rating=" + item.getRating() + "&contents=" + item.getContents(),
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
        GetStatusResponse getStatusResponse = gson.fromJson(response, GetStatusResponse.class);

        if(getStatusResponse.getCode() == 200){

            Log.d("getReviewRes", getStatusResponse.getResult());
            Intent resultIntent = new Intent();
            setResult(RESULT_OK,resultIntent);
            finish();
        }
    }

    private void setOnBtnClickListener() {
        btnCancel = (Button) findViewById(R.id.btn_write_review_act_cancel);
        btnSave = (Button) findViewById(R.id.btn_write_review_act_save);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCreateCommentResponse(id);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
