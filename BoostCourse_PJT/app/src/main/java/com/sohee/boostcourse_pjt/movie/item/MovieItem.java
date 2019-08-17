package com.sohee.boostcourse_pjt.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieItem implements Parcelable {

    int poster;
    String title;
    String age;
    String advance_rate;
    String d_day;


    public MovieItem(int poster, String title, String advance_rate, String age, String d_day) {
        this.poster = poster;
        this.title = title;
        this.advance_rate = advance_rate;
        this.age = age;
        this.d_day = d_day;
    }

    protected MovieItem(Parcel in) {
        title = in.readString();
        age = in.readString();
        poster = in.readInt();
        advance_rate = in.readString();
        d_day = in.readString();

    }

    public static final Creator<MovieItem> CREATOR = new Creator<MovieItem>() {
        @Override
        public MovieItem createFromParcel(Parcel in) {
            return new MovieItem(in);
        }

        @Override
        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

    @Override
    public String toString() {
        return "MovieItem{" +
                "title='" + title + '\'' +
                ", age='" + age + '\'' +
                ", poster='" + poster + '\'' +
                ", advance_rate='" + advance_rate + '\'' +
                ", d_day='" + d_day + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public String getAdvance_rate() {
        return advance_rate;
    }

    public void setAdvance_rate(String advance_rate) {
        this.advance_rate = advance_rate;
    }

    public String getD_day() {
        return d_day;
    }

    public void setD_day(String d_day) {
        this.d_day = d_day;
    }
}
