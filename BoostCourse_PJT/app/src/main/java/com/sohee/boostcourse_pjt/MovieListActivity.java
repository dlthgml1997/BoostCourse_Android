package com.sohee.boostcourse_pjt;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import com.sohee.boostcourse_pjt.adapter.MovieListAdapter;
import com.sohee.boostcourse_pjt.fragment.FirstFragment;
import com.sohee.boostcourse_pjt.fragment.SecondFragment;

public class MovieListActivity extends AppCompatActivity {

    FirstFragment firstFragment= new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        setAdapter();
    }

    private void setAdapter() {
        ViewPager pager = (ViewPager) findViewById(R.id.vp_movie_list_act);
        pager.setOffscreenPageLimit(3);

        MovieListAdapter adapter = new MovieListAdapter(getSupportFragmentManager());

        adapter.addItem(firstFragment);
        adapter.addItem(secondFragment);
//        adapter.addItem(firstFragment);
//        adapter.addItem(firstFragment);

        pager.setAdapter(adapter);
    }
}
