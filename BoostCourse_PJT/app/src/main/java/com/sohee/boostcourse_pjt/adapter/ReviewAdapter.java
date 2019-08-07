package com.sohee.boostcourse_pjt.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.sohee.boostcourse_pjt.ReviewItem;
import com.sohee.boostcourse_pjt.ReviewItemView;

import java.util.ArrayList;

public class ReviewAdapter extends BaseAdapter {
    ArrayList<ReviewItem> items = new ArrayList<ReviewItem>();
    @Override
    public int getCount() {
        return items.size();
    }

    public void addItem(ReviewItem item){
        items.add(item);
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ReviewItemView view = new ReviewItemView(Context());

        ReviewItem item = items.get(position);
        view.setId(item.getId());
        view.setTime(item.getTime());
        view.setRating(item.getRating());
        view.setReview(item.getReview());
        return view;
    }
}

