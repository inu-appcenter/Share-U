package com.inuappcenter.shareu.my_class;

import com.google.gson.annotations.SerializedName;

public class Document {

    @SerializedName("title")
    String title;
    @SerializedName("uploadDate")
    String uploadDate;
    @SerializedName("documentKey")
    int documentKey;
    @SerializedName("extension")
    String extension;
    @SerializedName("subjectName")
    String subjectName;
    @SerializedName("profName")
    String profName;
    @SerializedName("content")
    String content;
    @SerializedName("avg(re.score)")
    int avg_score;

    public Document(String title, String uploadDate, int documentKey, String extension, String subjectName, String profName, String content, int avg_score) {
        this.title = title;
        this.uploadDate = uploadDate;
        this.documentKey = documentKey;
        this.extension = extension;
        this.subjectName = subjectName;
        this.profName = profName;
        this.content = content;
        this.avg_score = avg_score;
    }

    public String getTitle() {
        return title;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public int getDocumentKey() {
        return documentKey;
    }

    public String getExtension() {
        return extension;
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

    public int getAvg_score() {
        return avg_score;
    }
}
