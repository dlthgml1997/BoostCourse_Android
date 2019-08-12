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

public class MovieListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected FirstFragment firstFragment = new FirstFragment();
    protected SecondFragment secondFragment = new SecondFragment();
    protected ThirdFragment thirdFragment = new ThirdFragment();
    protected ForthFragment forthFragment = new ForthFragment();
    protected FifthFragment fifthFragment = new FifthFragment();
    protected MovieDetailFragment movieDetailFragment = new MovieDetailFragment();

    protected Toolbar toolbar;
    protected NavigationView navigationView;
    protected DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        setAdapter();
        setToolbar();
        setDrawer();
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar_movie_list_act);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("영화 목록");
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

    private void setDrawer() {
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

        DrawerLayout drawer = findViewById(R.id.drawer_main_act);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_main_act);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void changeActionBarTitleToMovieDetail() {
        setToolbar();
        getSupportActionBar().setTitle("영화 상세");
    }

    public void onFragmentChange(int index) {
        switch (index) {
            case 0:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fl_movie_list_act, movieDetailFragment)
                        .commit();
                break;
        }
    }
}
