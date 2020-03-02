package com.inuappcenter.shareu.my_class;

import com.google.gson.annotations.SerializedName;

public class documentPage
{

    @SerializedName("division")
    String division;
    @SerializedName("majorName")
    String majorName;
    @SerializedName("title")
    String title;
    @SerializedName("subjectName")
    String subjectName;
    @SerializedName("profName")
    String profName;
    @SerializedName("content")
    String content;
    @SerializedName("uploadDate")
    String uploadDate;
    @SerializedName("uploadId")
    String uploadId;
    @SerializedName("extension")
    String extension;

    public String getDivision() {
        return division;
    }

    public String getMajorName() {
        return majorName;
    }

    public String getTitle() {
        return title;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getProfName() {
        return profName;
    }

    public String getContent() {
        return content;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public String getUploadId() {
        return uploadId;
    }

    public String getExtension() {
        return extension;
    }
}
