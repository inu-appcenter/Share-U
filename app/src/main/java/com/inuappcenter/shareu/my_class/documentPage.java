package com.inuappcenter.shareu.my_class;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class documentPage
{
    @SerializedName("ans")
    public boolean ans;

    @SerializedName("rows")
    public List<Row> rows;

    public class Row{

        @SerializedName("title")
        public String title;
        @SerializedName("subjectName")
        public String subjectName;
        @SerializedName("profName")
        public String profName;
        @SerializedName("content")
        public String content;
        @SerializedName("uploadDate")
        public String uploadDate;
        @SerializedName("uploadId")
        public String uploadId;
        @SerializedName("extension")
        public String extension;
        @SerializedName("majorName")
        public String majorName;
    }



    /*@SerializedName("majorName")
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
    }*/
}
