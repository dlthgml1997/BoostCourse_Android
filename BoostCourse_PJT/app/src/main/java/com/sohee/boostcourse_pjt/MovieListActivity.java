package com.sohee.boostcourse_pjt;

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
import com.sohee.boostcourse_pjt.adapter.MovieListAdapter;
import com.sohee.boostcourse_pjt.fragment.*;
import com.sohee.boostcourse_pjt.model.MovieItem;

import java.util.List;

public class MovieListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected MovieListFragment movieListFragment = new MovieListFragment();
    protected SecondFragment secondFragment = new SecondFragment();
    protected ThirdFragment thirdFragment = new ThirdFragment();
    protected ForthFragment forthFragment = new ForthFragment();
    protected FifthFragment fifthFragment = new FifthFragment();
    protected MovieDetailFragment movieDetailFragment = new MovieDetailFragment();

    protected Toolbar toolbar;
    protected NavigationView navigationView;
    protected DrawerLayout drawer;

    private List<MovieItem> movieItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        setAdapter();
        setToolbar();
        setDrawer();
    }


    private void addMovieItem() {
    }

    public void setToolbar() {
        toolbar = findViewById(R.id.toolbar_movie_list_act);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("영화 목록");
    }

    private void setAdapter() {
        ViewPager pager = (ViewPager) findViewById(R.id.vp_movie_list_act);
        pager.setOffscreenPageLimit(3);


        movieItems.add(new MovieItem(R.drawable.image1, "1. 군 도", "61.6%", "15세 관람가", "D-1"));
        movieItems.add(new MovieItem(R.drawable.image2, "2. 군 도", "61.6%", "15세 관람가", "D-1"));
        movieItems.add(new MovieItem(R.drawable.image3, "3. 군 도", "61.6%", "15세 관람가", "D-1"));
        movieItems.add(new MovieItem(R.drawable.image4, "4. 군 도", "61.6%", "15세 관람가", "D-1"));
        movieItems.add(new MovieItem(R.drawable.image5, "5. 군 도", "61.6%", "15세 관람가", "D-1"));

        MovieListAdapter adapter = new MovieListAdapter(getSupportFragmentManager());


        for (int i = 0; i <= movieItems.size(); i++) {
            MovieListFragment fragment = MovieListFragment.getInstance(movieItems.get(i));
            fragment.getArguments();
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

                Intent intent = new Intent(getApplicationContext(), MovieListActivity.class);
                startActivity(intent);

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

    public void changeActionBarTitleToMovieDetail() {
        getSupportActionBar().setTitle("영화 상세");
    }

    public void onFragmentChange(int index) {
        switch (index) {
            case 0:
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fl_movie_list_act, movieDetailFragment)
                        .commit();

                break;
        }
    }
}
