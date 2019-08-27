package com.sohee.boostcourse_pjt.ui.review.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.sohee.boostcourse_pjt.network.AppHelper;
import com.sohee.boostcourse_pjt.ui.review.get.GetStatusResponse;
import com.sohee.boostcourse_pjt.ui.review.item.ReviewItem;
import com.sohee.boostcourse_pjt.ui.review.view.ReviewItemView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReviewAdapter extends BaseAdapter {
    ArrayList<ReviewItem> items = new ArrayList<ReviewItem>();

    @Override
    public int getCount() {
        return items.size();
    }

    public void addItem(ReviewItem item) {
        items.add(item);
    }

    public void setItems(ArrayList<ReviewItem> getItems) {
        items = getItems;
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    public ArrayList<ReviewItem> getItems() {
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

        final ReviewItem item = items.get(position);
        view.setTime(item.getTime());
        view.setRating(item.getRating());
        view.setReview(item.getContents());
        view.setRecommend(item.getRecommend() + "");
        view.getBtnRecommend().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIncreaseRecommendRes(item.getReview_id(),item.getWriter());
            }
        });


        return view;
    }

    private void getIncreaseRecommendRes(int review_id,String writer) {

        StringRequest request = new StringRequest(
                Request.Method.GET,
                AppHelper.baseUrl + "movie/increaseRecommend?review_id=" + review_id + "&writer="+writer,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("getReviewRes", "응답 -> " + response);

                        processRecommendResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("getReviewRes", "에러 -> " + error);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
        Log.d("getReviewRes", "요청 보냄.");
    }

    private void processRecommendResponse(String response) {
        Gson gson = new Gson();
        GetStatusResponse getStatusResponse = gson.fromJson(response, GetStatusResponse.class);

        Log.d("getReviewRes", getStatusResponse.getCode()+"");
        if(getStatusResponse.getCode() == 200){

        }
    }
}

