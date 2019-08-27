package com.sohee.boostcourse_pjt.ui.review.item;

import android.os.Parcel;
import android.os.Parcelable;

public class ReviewItem implements Parcelable {

    int _id;
    String movie_title;
    int total;

    String writer;
    int review_id;
    String writer_image;
    String time;
    String time_stamp;
    Float rating;
    String contents;
    int recommend;

    public ReviewItem(int _id, String movie_title, int total, String writer, int review_id, String writer_image, String time, String time_stamp, Float rating, String contents, int recommend) {
        this._id = _id;
        this.movie_title = movie_title;
        this.total = total;
        this.writer = writer;
        this.review_id = review_id;
        this.writer_image = writer_image;
        this.time = time;
        this.time_stamp = time_stamp;
        this.rating = rating;
        this.contents = contents;
        this.recommend = recommend;
    }

    protected ReviewItem(Parcel in) {
        _id = in.readInt();
        movie_title = in.readString();
        total = in.readInt();
        writer = in.readString();
        review_id = in.readInt();
        writer_image = in.readString();
        time = in.readString();
        time_stamp = in.readString();
        rating = in.readFloat();
        contents = in.readString();
        recommend = in.readInt();
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

    public int get_Id() {
        return _id;
    }

    public String getMovieTitle() {
        return movie_title;
    }

    public void setMovieTitle(String movie_title) {
        this.movie_title = movie_title;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public String getWriter_image() {
        return writer_image;
    }

    public void setWriter_image(String writer_image) {
        this.writer_image = writer_image;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public static Creator<ReviewItem> getCREATOR() {
        return CREATOR;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
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

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "ReviewItem{" +
                "_id=" + _id +
                ", movie_title='" + movie_title + '\'' +
                ", total=" + total +
                ", writer='" + writer + '\'' +
                ", review_id=" + review_id +
                ", writer_image='" + writer_image + '\'' +
                ", time='" + time + '\'' +
                ", time_stamp='" + time_stamp + '\'' +
                ", rating=" + rating +
                ", contents='" + contents + '\'' +
                ", recommend=" + recommend +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(_id);
        parcel.writeString(movie_title);
        parcel.writeInt(total);
        parcel.writeString(writer);
        parcel.writeInt(review_id);
        parcel.writeString(writer_image);
        parcel.writeString(time);
        parcel.writeString(time_stamp);
        parcel.writeFloat(rating);
        parcel.writeString(contents);
        parcel.writeInt(recommend);
    }
}
