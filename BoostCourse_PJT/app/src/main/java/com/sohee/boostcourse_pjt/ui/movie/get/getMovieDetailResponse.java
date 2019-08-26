package com.sohee.boostcourse_pjt.ui.movie.get;

import com.sohee.boostcourse_pjt.ui.movie.item.MovieDetailItem;
import com.sohee.boostcourse_pjt.ui.movie.item.MovieItem;

import java.util.ArrayList;

public class GetMovieDetailResponse {
    String message;
    int code;
    String resultType;
    public ArrayList<MovieDetailItem> result = new ArrayList<MovieDetailItem>();

}
