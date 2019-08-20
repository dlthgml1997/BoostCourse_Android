package com.sohee.boostcourse_pjt.ui.review.get;


import com.sohee.boostcourse_pjt.ui.review.model.ReviewItem;

import java.util.ArrayList;

public class getReviewListResponse {
    String message;
    int code;
    String resultType;
    int totalCount;
    public ArrayList<ReviewItem> result = new ArrayList<ReviewItem>();
}
