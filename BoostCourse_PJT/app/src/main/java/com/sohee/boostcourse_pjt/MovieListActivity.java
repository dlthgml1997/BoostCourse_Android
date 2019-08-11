package com.sohee.boostcourse_pjt;

import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import com.sohee.boostcourse_pjt.adapter.MovieListAdapter;
import com.sohee.boostcourse_pjt.fragment.*;

public class MovieListActivity extends AppCompatActivity {

    FirstFragment firstFragment= new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    ThirdFragment thirdFragment = new ThirdFragment();
    ForthFragment forthFragment = new ForthFragment();
    FifthFragment fifthFragment = new FifthFragment();
    MovieDetailFragment movieDetailFragment = new MovieDetailFragment();


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
        adapter.addItem(thirdFragment);
        adapter.addItem(forthFragment);
        adapter.addItem(fifthFragment);

        pager.setAdapter(adapter);
    }

    public void setFrag() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if(!isDestroyed() || !isFinishing()) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.fl_movie_list_act, movieDetailFragment).commit();
            }
        }
    }
}
