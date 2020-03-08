package com.inuappcenter.shareu.my_class;

import com.google.gson.annotations.SerializedName;

public class MyUpload {

    @SerializedName("uploadDate")
    String uploadDate;
    @SerializedName("title")
    String title;
    @SerializedName("extension")
    String extension;
    @SerializedName("documentKey")
    int documentKey;

    public MyUpload(String uploadDate, String title, String extension, int documentKey) {
        this.uploadDate = uploadDate;
        this.title = title;
        this.extension = extension;
        this.documentKey = documentKey;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public String getTitle() {
        return title;
    }

    public String getExtension() {
        return extension;
    }

    public int getDocumentKey() {
        return documentKey;
    }
}
