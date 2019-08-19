package com.sohee.boostcourse_pjt.ui.movie.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class MovieListAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> MovieListItems = new ArrayList<Fragment>();

    public MovieListAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addItem(Fragment item){
        MovieListItems.add(item);
    }

    @Override
    public Fragment getItem(int position) {
        return MovieListItems.get(position);
    }

    @Override
    public int getCount() {
        return MovieListItems.size();
    }

}
