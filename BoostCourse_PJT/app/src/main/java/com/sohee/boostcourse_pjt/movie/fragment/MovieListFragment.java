package com.sohee.boostcourse_pjt.movie.fragment;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.sohee.boostcourse_pjt.AppHelper;
import com.sohee.boostcourse_pjt.MovieList;
import com.sohee.boostcourse_pjt.R;
import com.sohee.boostcourse_pjt.movie.item.MovieItem;
import com.sohee.boostcourse_pjt.network.Network;

import java.util.HashMap;
import java.util.Map;

public class MovieListFragment extends Fragment {

    private Button btnMoreInfo;
    private ImageView imgPoster;
    private TextView txtTitle;
    private TextView txtAge;
    private TextView txtDday;
    private TextView txtAdvanced;

    private MovieItem item;

    private onFragmentChangeListener onFragmentChangeListener;

    private String baseUrl = Network.baseUrl;

    public MovieListFragment() {
        // Required empty public constructor
    }

    public static MovieListFragment newInstance(MovieItem movieitem) {

        Bundle bundle = new Bundle();
        bundle.putParcelable("movieItem", movieitem);

        MovieListFragment fragment = new MovieListFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        onFragmentChangeListener = (onFragmentChangeListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        onFragmentChangeListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = (View) inflater.inflate(R.layout.fragment_movie_list, container, false);

        btnMoreInfo = (Button) view.findViewById(R.id.btn_first_frag_more_info);
        imgPoster = (ImageView) view.findViewById(R.id.img_first_frag_poster);
        txtTitle = (TextView) view.findViewById(R.id.txt_first_frag_title);
        txtAdvanced = (TextView) view.findViewById(R.id.txt_first_frag_advance_rate);
        txtAge = (TextView) view.findViewById(R.id.txt_first_frag_age);
        txtDday = (TextView) view.findViewById(R.id.txt_first_frag_d_day);
        btnMoreInfo = (Button) view.findViewById(R.id.btn_first_frag_more_info);

        setOnBtnClickListener();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setContent();
        getMovieListResponse();
    }

    private void getMovieListResponse() {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                baseUrl+"/movie/readMovieList?type=1",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("MovieList", "응답 -> " + response);

                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("MovieList","에러 -> " + error);
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
        Log.d("MovieList","요청 보냄.");
    }


    private void processResponse(String response) {
        Gson gson = new Gson();
        MovieList movieList = gson.fromJson(response, MovieList.class);

        if (movieList != null) {

        }
    }


    public void setContent() {

        item = getArguments().getParcelable("movieItem");

        imgPoster.setImageResource(item.getPoster());
        txtTitle.setText(item.getTitle());
        txtAge.setText(item.getAge());
        txtDday.setText(item.getD_day());
        txtAdvanced.setText(item.getAdvance_rate());
    }

    private void setOnBtnClickListener() {
        btnMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFragmentChangeListener.onFragmentChange();
            }
        });
    }

    public interface onFragmentChangeListener {
        public void onFragmentChange();
    }

}
