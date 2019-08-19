package com.sohee.boostcourse_pjt.movie;

import android.content.Intent;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.navigation.NavigationView;
import com.sohee.boostcourse_pjt.R;
import com.sohee.boostcourse_pjt.movie.adapter.MovieListAdapter;
import com.sohee.boostcourse_pjt.movie.fragment.*;
import com.sohee.boostcourse_pjt.movie.item.MovieItem;
import com.sohee.boostcourse_pjt.review.ReviewDetailActivity;
import com.sohee.boostcourse_pjt.review.WriteReviewActivity;
import com.sohee.boostcourse_pjt.review.model.ReviewItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MovieDetailFragment.onReplaceFragmentListener, MovieListFragment.onFragmentChangeListener {

    protected MovieDetailFragment movieDetailFragment = new MovieDetailFragment();

    protected Toolbar toolbar;
    protected NavigationView navigationView;
    protected DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setAdapter();
        setToolbar();
        setDrawer();
    }

    public void setToolbar() {
        toolbar = findViewById(R.id.toolbar_movie_list_act);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("영화 목록");
    }

    private void setAdapter() {
        ViewPager pager = (ViewPager) findViewById(R.id.vp_movie_list_act);
        pager.setOffscreenPageLimit(3);

        ArrayList<MovieItem> movieItems = new ArrayList<MovieItem>();
        movieItems.add(new MovieItem(R.drawable.image1, "1. 군 도", "61.6%", "15세 관람가", "D-1"));
        movieItems.add(new MovieItem(R.drawable.image2, "2. 공 조", "59.6", "12세 관람가", "D-1"));
        movieItems.add(new MovieItem(R.drawable.image3, "3. 더 킹", "61.6%", "19세 관람가", "D-1"));
        movieItems.add(new MovieItem(R.drawable.image4, "4. 레지던트 이블", "61.6%", "15세 관람가", "D-1"));
        movieItems.add(new MovieItem(R.drawable.image5, "5. 럭 키", "61.6%", "12세 관람가", "D-1"));

        MovieListAdapter adapter = new MovieListAdapter(getSupportFragmentManager());

        for (int i = 0; i < movieItems.size(); i++) {
            MovieListFragment fragment = MovieListFragment.newInstance(movieItems.get(i));
            adapter.addItem(fragment);
        }

        pager.setAdapter(adapter);
    }

    public void setDrawer() {
        drawer = findViewById(R.id.drawer_main_act);

        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case R.id.nav_menu_list:

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                //추가함
                finish();

                break;

            case R.id.nav_menu_api:
                break;
            case R.id.nav_menu_reserve:
                break;
            case R.id.nav_menu_setting:
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void changeActionBarTitleToMovieDetail() {
        getSupportActionBar().setTitle("영화 상세");
    }


    @Override
    public void onFragmentChange() {

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fl_movie_list_act, movieDetailFragment)
                .commit();
    }

    @Override
    public void startWriteReviewAct() {
        Intent intent = new Intent(getApplicationContext(), WriteReviewActivity.class);

        startActivity(intent);
    }

    @Override
    public void startReviewDetailAct(ArrayList<ReviewItem> reviewItems) {
        Intent intent = new Intent(getApplicationContext(), ReviewDetailActivity.class);
        intent.putExtra("reviewItems", reviewItems);

        startActivity(intent);
    }
}
