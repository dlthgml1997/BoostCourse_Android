package com.sohee.boostcourse_pjt;

import android.os.Parcel;
import android.os.Parcelable;

public class ReviewItem implements Parcelable {

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

    protected ReviewItem(Parcel in) {
        id = in.readString();
        time = in.readString();
        rating = in.readFloat();
        review = in.readString();
    }

    public static final Creator<ReviewItem> CREATOR = new Creator<ReviewItem>() {
        @Override
        public ReviewItem createFromParcel(Parcel in) {
            return new ReviewItem(in);
        }

        @Override
        public ReviewItem[] newArray(int size) {
            return new ReviewItem[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(time);
        parcel.writeFloat(rating);
        parcel.writeString(review);
    }
}
