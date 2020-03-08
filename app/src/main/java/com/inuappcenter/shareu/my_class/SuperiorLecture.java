package com.inuappcenter.shareu.my_class;

import com.google.gson.annotations.SerializedName;

public class SuperiorLecture {

    @SerializedName("documentKey")
    int documentKey;
    @SerializedName("title")
    String title;
    @SerializedName("uploadDate")
    String uploadDate;
    @SerializedName("extension")
    String extension;
    @SerializedName("avg(r.score)")
    float rating;

    public SuperiorLecture(int documentKey, String title, String uploadDate, String extension, float rating) {
        this.documentKey = documentKey;
        this.title = title;
        this.uploadDate = uploadDate;
        this.extension = extension;
        this.rating = rating;
    }

    public int getDocumentKey() {
        return documentKey;
    }

    public String getTitle() {
        return title;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public String getExtension() {
        return extension;
    }

    public float getRating() {
        return rating;
    }
}
