package com.sohee.boostcourse_pjt.ui.movie;

import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.sohee.boostcourse_pjt.network.AppHelper;
import com.sohee.boostcourse_pjt.network.DBHelper;
import com.sohee.boostcourse_pjt.ui.movie.get.GetMovieListResponse;
import com.sohee.boostcourse_pjt.R;
import com.sohee.boostcourse_pjt.ui.movie.adapter.MovieListAdapter;
import com.sohee.boostcourse_pjt.ui.movie.fragment.*;
import com.sohee.boostcourse_pjt.ui.movie.item.MovieItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.sohee.boostcourse_pjt.network.NetworkStatus.hasInternetConnection;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MovieDetailFragment.onReplaceFragmentListener, MovieListFragment.onFragmentChangeListener {

    protected Toolbar toolbar;
    protected NavigationView navigationView;
    protected DrawerLayout drawer;

    private String baseUrl = AppHelper.baseUrl;
    private ArrayList<MovieItem> movieItems = new ArrayList<MovieItem>();
    private MovieListAdapter adapter;

    private boolean status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status = hasInternetConnection(getApplicationContext());

        setDB();

        getRequestQueue();
        getMovieListResponse();

        setToolbar();
        setDrawer();
    }

    private void setDB() {
        DBHelper.openDatabase(getApplicationContext(), "movie");
        DBHelper.createTable("outline");
        DBHelper.createTable("inline");
        DBHelper.createTable("review");
    }

    private void selectOutlineData() {
        movieItems = (ArrayList<MovieItem>) DBHelper.selectTable("outline");
        Log.d("DBHelper", movieItems.toString());
        setAdapter(movieItems);
    }

    private void getRequestQueue() {
        if (AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    private void getMovieListResponse() {
        if (status) {
            StringRequest request = new StringRequest(
                    Request.Method.GET,
                    baseUrl + "/movie/readMovieList?type=1",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("GetMovieListResponse", "응답 -> " + response);

                            processResponse(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("GetMovieListResponse", "에러 -> " + error);
                        }
                    }

            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    return params;
                }
            };
            //매번 받은 결과를 그대로 보여주세요
            request.setShouldCache(false);

            AppHelper.requestQueue.add(request);
            Log.d("GetMovieListResponse", "요청 보냄.");
        } else {
            selectOutlineData();
        }
    }

    private void processResponse(String response) {
        Gson gson = new Gson();
        GetMovieListResponse getMovieListResponse = gson.fromJson(response, GetMovieListResponse.class);

        if (status) {
            if (getMovieListResponse != null) {
                Log.d("GetMovieListResponse", getMovieListResponse.result.toString());
                movieItems = getMovieListResponse.result;
                setAdapter(movieItems);

                    DBHelper.insertOutlineData(movieItems);
            }
        }
    }

    public void setToolbar() {
        toolbar = findViewById(R.id.toolbar_movie_list_act);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("영화 목록");
    }

    private void setAdapter(ArrayList<MovieItem> movieItems) {
        ViewPager pager = (ViewPager) findViewById(R.id.vp_movie_list_act);
        pager.setOffscreenPageLimit(3);

        adapter = new MovieListAdapter(getSupportFragmentManager());

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
    public void onFragmentChange(int id, String title) {

        MovieDetailFragment movieDetailFragment = MovieDetailFragment.newInstance(id, title);

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fl_movie_list_act, movieDetailFragment)
                .commit();
    }


}


