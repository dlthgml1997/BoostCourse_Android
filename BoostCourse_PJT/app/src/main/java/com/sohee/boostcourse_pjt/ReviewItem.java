package com.sohee.boostcourse_pjt;

public class ReviewItem {

    String id;
    String time;
    Float rating;
    String review;

    public ReviewItem(String id, String time, Float rating, String review) {
        this.id = id;
        this.time = time;
        this.rating = rating;
        this.review = review;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "ReviewItem{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", rating='" + rating + '\'' +
                ", review='" + review + '\'' +
                '}';
    }
}
