package com.sohee.boostcourse_pjt.ui.review.get;


import com.sohee.boostcourse_pjt.ui.review.item.ReviewItem;

import java.util.ArrayList;

public class GetReviewListResponse {
    String message;
    int code;
    String resultType;
    int totalCount;
    public ArrayList<ReviewItem> result = new ArrayList<ReviewItem>();
}
