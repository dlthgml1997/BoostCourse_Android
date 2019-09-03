package com.sohee.boostcourse_pjt.ui.movie.item;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieItem implements Parcelable {


    int _id;
    int id;
    String title;
    String title_eng;
    String date;
    Float user_rating;
    Float audience_rating;
    Float reviewer_rating;
    Float reservation_rate;
    int reservation_grade;
    int grade;
    String thumb;
    String image;

    public MovieItem(int _id,int id, String image, String title, String title_eng, Float audience_rating, String date, Float user_rating, Float reviewer_rating, Float reservation_rate, int reservation_grade, int grade, String thumb) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.title_eng = title_eng;
        this.date = date;
        this.user_rating = user_rating;
        this.audience_rating = audience_rating;
        this.reviewer_rating = reviewer_rating;
        this.reservation_rate = reservation_rate;
        this.reservation_grade = reservation_grade;
        this.grade = grade;
        this.thumb = thumb;
    }

    @Override
    public String toString() {
        return "MovieItem{" +
                "_id=" + _id +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", title_eng='" + title_eng + '\'' +
                ", date='" + date + '\'' +
                ", user_rating=" + user_rating +
                ", audience_rating=" + audience_rating +
                ", reviewer_rating=" + reviewer_rating +
                ", reservation_rate=" + reservation_rate +
                ", reservation_grade=" + reservation_grade +
                ", grade=" + grade +
                ", thumb='" + thumb + '\'' +
                ", photo='" + image + '\'' +
                '}';
    }

    protected MovieItem(Parcel in) {
        _id = in.readInt();
        id = in.readInt();
        image = in.readString();
        title = in.readString();
        title_eng = in.readString();
        date = in.readString();
        if (in.readByte() == 0) {
            user_rating = null;
        } else {
            user_rating = in.readFloat();
        }
        if (in.readByte() == 0) {
            reviewer_rating = null;
        } else {
            reviewer_rating = in.readFloat();
        }
        if (in.readByte() == 0) {
            reservation_rate = null;
        } else {
            reservation_rate = in.readFloat();
        }
        if (in.readByte() == 0) {
            audience_rating = null;
        } else {
            audience_rating = in.readFloat();
        }
        reservation_grade = in.readInt();
        grade = in.readInt();
        thumb = in.readString();

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

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Float getAudienceRating() {
        return audience_rating;
    }

    public void setAudienceRating(Float audience_rating) {
        this.audience_rating = audience_rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_eng() {
        return title_eng;
    }

    public void setTitle_eng(String title_eng) {
        this.title_eng = title_eng;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(Float user_rating) {
        this.user_rating = user_rating;
    }

    public Float getReviewer_rating() {
        return reviewer_rating;
    }

    public void setReviewer_rating(Float reviewer_rating) {
        this.reviewer_rating = reviewer_rating;
    }

    public Float getReservation_rate() {
        return reservation_rate;
    }

    public void setReservation_rate(Float reservation_rate) {
        this.reservation_rate = reservation_rate;
    }

    public int getReservation_grade() {
        return reservation_grade;
    }

    public void setReservation_grade(int reservation_grade) {
        this.reservation_grade = reservation_grade;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(image);
        parcel.writeString(title);
        parcel.writeString(title_eng);
        parcel.writeString(date);
        if (user_rating == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(user_rating);
        }
        if (reviewer_rating == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(reviewer_rating);
        }
        if (audience_rating == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(audience_rating);
        }
        if (reservation_rate == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(reservation_rate);
        }
        parcel.writeInt(reservation_grade);
        parcel.writeInt(grade);
        parcel.writeString(thumb);
    }
}
