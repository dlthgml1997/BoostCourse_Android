package com.sohee.boostcourse_pjt.ui.movie.get;

import com.sohee.boostcourse_pjt.ui.movie.item.MovieItem;

import java.util.ArrayList;

public class getMovieListResponse {
    String message;
    int code;
    String resultType;
    public ArrayList<MovieItem> result = new ArrayList<MovieItem>();

}
