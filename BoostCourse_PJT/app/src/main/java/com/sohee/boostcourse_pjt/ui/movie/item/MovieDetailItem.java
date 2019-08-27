package com.sohee.boostcourse_pjt.ui.movie.item;

import android.os.Parcel;
import android.os.Parcelable;


public class MovieDetailItem implements Parcelable {

    int id;
    String title;
    String date;
    Float user_rating;
    Float audience_rating;
    Float reviewer_rating;
    Float reservation_rate;
    int reservation_grade;
    int grade;
    String thumb;
    String image;
    String photos;
    String videos;
    String outlinks;
    String genre;
    int duration;
    int audience;
    String synopsis;
    String director;
    String actor;
    int like;
    int dislike;

    public MovieDetailItem(int id, String title, String date, Float user_rating, Float audience_rating, Float reviewer_rating, Float reservation_rate, int reservation_grade, int grade, String thumb, String image, String photos, String videos, String outlinks, String genre, int duration, int audience, String synopsis, String director, String actor, int like, int dislike) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.user_rating = user_rating;
        this.audience_rating = audience_rating;
        this.reviewer_rating = reviewer_rating;
        this.reservation_rate = reservation_rate;
        this.reservation_grade = reservation_grade;
        this.grade = grade;
        this.thumb = thumb;
        this.image = image;
        this.photos = photos;
        this.videos = videos;
        this.outlinks = outlinks;
        this.genre = genre;
        this.duration = duration;
        this.audience = audience;
        this.synopsis = synopsis;
        this.director = director;
        this.actor = actor;
        this.like = like;
        this.dislike = dislike;
    }

    public MovieDetailItem(Parcel in) {
        title = in.readString();
        id = in.readInt();
        date = in.readString();
        if (in.readByte() == 0) {
            user_rating = null;
        } else {
            user_rating = in.readFloat();
        }
        if (in.readByte() == 0) {
            audience_rating = null;
        } else {
            audience_rating = in.readFloat();
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
        reservation_grade = in.readInt();
        grade = in.readInt();
        thumb = in.readString();
        image = in.readString();
        photos = in.readString();
        videos = in.readString();
        outlinks = in.readString();
        genre = in.readString();
        duration = in.readInt();
        audience = in.readInt();
        synopsis = in.readString();
        director = in.readString();
        actor = in.readString();
        like = in.readInt();
        dislike = in.readInt();
    }

    @Override
    public String toString() {
        return "MovieDetailItem{" +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", user_rating=" + user_rating +
                ", audience_rating=" + audience_rating +
                ", reviewer_rating=" + reviewer_rating +
                ", reservation_rate=" + reservation_rate +
                ", reservation_grade=" + reservation_grade +
                ", grade=" + grade +
                ", thumb='" + thumb + '\'' +
                ", image='" + image + '\'' +
                ", photos='" + photos + '\'' +
                ", videos='" + videos + '\'' +
                ", outlinks='" + outlinks + '\'' +
                ", genre='" + genre + '\'' +
                ", duration=" + duration +
                ", audience=" + audience +
                ", synopsis='" + synopsis + '\'' +
                ", director='" + director + '\'' +
                ", actor='" + actor + '\'' +
                ", like=" + like +
                ", dislike=" + dislike +
                '}';
    }

    public static final Creator<MovieDetailItem> CREATOR = new Creator<MovieDetailItem>() {
        @Override
        public MovieDetailItem createFromParcel(Parcel in) {
            return new MovieDetailItem(in);
        }

        @Override
        public MovieDetailItem[] newArray(int size) {
            return new MovieDetailItem[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Float getAudience_rating() {
        return audience_rating;
    }

    public void setAudience_rating(Float audience_rating) {
        this.audience_rating = audience_rating;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }

    public String getOutlinks() {
        return outlinks;
    }

    public void setOutlinks(String outlinks) {
        this.outlinks = outlinks;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getAudience() {
        return audience;
    }

    public void setAudience(int audience) {
        this.audience = audience;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeInt(id);
        parcel.writeString(date);
        if (user_rating == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(user_rating);
        }
        if (audience_rating == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(audience_rating);
        }
        if (reviewer_rating == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(reviewer_rating);
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
        parcel.writeString(image);
        parcel.writeString(photos);
        parcel.writeString(videos);
        parcel.writeString(outlinks);
        parcel.writeString(genre);
        parcel.writeInt(duration);
        parcel.writeInt(audience);
        parcel.writeString(synopsis);
        parcel.writeString(director);
        parcel.writeString(actor);
        parcel.writeInt(like);
        parcel.writeInt(dislike);
    }
}
