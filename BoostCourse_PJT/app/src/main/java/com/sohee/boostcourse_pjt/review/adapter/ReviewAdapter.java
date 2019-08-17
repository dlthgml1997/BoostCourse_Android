package com.sohee.boostcourse_pjt.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.sohee.boostcourse_pjt.model.ReviewItem;
import com.sohee.boostcourse_pjt.review.ReviewItemView;

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

    public void setItems(ArrayList<ReviewItem> getItems) {items = getItems;}

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    public ArrayList<ReviewItem> getItems(){
        return items;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        ReviewItemView view = new ReviewItemView(context);

        ReviewItem item = items.get(position);
        view.setId(item.getId());
        view.setTime(item.getTime());
        view.setRating(item.getRating());
        view.setReview(item.getReview());
        return view;
    }
}

