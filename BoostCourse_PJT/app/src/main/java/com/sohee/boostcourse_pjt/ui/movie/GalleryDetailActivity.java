package com.sohee.boostcourse_pjt.ui.movie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.chrisbanes.photoview.PhotoView;
import com.sohee.boostcourse_pjt.R;

public class GalleryDetailActivity extends AppCompatActivity {
    String photo;

    PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_detail);

        setToolbar();
        setPhoto();
    }

    private void setPhoto() {
        photo = getIntent().getStringExtra("photo");

        photoView = (PhotoView) findViewById(R.id.photoView);
        Glide.with(getApplicationContext()).load(photo).into(photoView);

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
}
