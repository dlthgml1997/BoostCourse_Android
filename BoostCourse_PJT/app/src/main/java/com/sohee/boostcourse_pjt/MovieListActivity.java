package com.sohee.boostcourse_pjt;

import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import com.sohee.boostcourse_pjt.adapter.MovieListAdapter;
import com.sohee.boostcourse_pjt.fragment.*;

public class MovieListActivity extends AppCompatActivity {

    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    ThirdFragment thirdFragment = new ThirdFragment();
    ForthFragment forthFragment = new ForthFragment();
    FifthFragment fifthFragment = new FifthFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        setAdapter();
        setToolbar();
    }


    private void setToolbar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setLogo(R.drawable.ic_hamburger_menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curi = item.getItemId();
        switch (curi)
        {
            case R.id.menu_hamberger :

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void changeActionBarTitleToMovieDetail() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("영화 상세");
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
}
