package com.sohee.boostcourse_pjt.ui.movie.item;

public class GalleryItem {
    boolean isVideo;
    String link;

    public GalleryItem(boolean isVideo, String link) {
        this.isVideo = isVideo;
        this.link = link;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "GalleryItem{" +
                "isVideo=" + isVideo +
                ", link=" + link +
                '}';
    }
}
