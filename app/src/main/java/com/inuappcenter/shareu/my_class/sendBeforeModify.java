package com.inuappcenter.shareu.my_class;

import com.google.gson.annotations.SerializedName;

public class sendBeforeModify {

    @SerializedName("title")
    String title;
    @SerializedName("subjectName")
    String subjectName;
    @SerializedName("profName")
    String profName;
    @SerializedName("content")
    String content;
    @SerializedName("extension")
    String extension;
    @SerializedName("fileName")
    String fileName;

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

    public String getExtension() {
        return extension;
    }

    public String getFileName() {
        return fileName;
    }
}
