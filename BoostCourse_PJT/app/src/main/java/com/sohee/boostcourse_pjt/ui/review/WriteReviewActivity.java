package com.sohee.boostcourse_pjt.ui.review;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.sohee.boostcourse_pjt.R;
import com.sohee.boostcourse_pjt.ui.movie.fragment.MovieDetailFragment;

public class WriteReviewActivity extends AppCompatActivity {

    private Button btnCancel;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);
        setOnBtnClickListener();
    }

    private void setOnBtnClickListener() {
        btnCancel = (Button) findViewById(R.id.btn_write_review_act_cancel);
        btnSave = (Button)findViewById(R.id.btn_write_review_act_save);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MovieDetailFragment.class);
                intent.putExtra("reviewResult","저장되었습니다.");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
