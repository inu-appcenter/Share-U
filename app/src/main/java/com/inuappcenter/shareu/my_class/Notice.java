package com.inuappcenter.shareu.my_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notice {
    @SerializedName("title")
    @Expose
    String title;
    @SerializedName("content")
    @Expose
    String content;

    @SerializedName("noticeDate")
    @Expose
    String noticeDate;

    @SerializedName("noticeKey")
    @Expose
    int noticeKey;


    int image;

    public Notice(String title, String content, String noticeDate, int noticeKey, int image) {
        this.title = title;
        this.content = content;
        this.noticeDate = noticeDate;
        this.noticeKey = noticeKey;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getNoticeDate() {
        return noticeDate;
    }

    public int getNoticeKey() {
        return noticeKey;
    }

    public int getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate;
    }

    public void setNoticeKey(int noticeKey) {
        this.noticeKey = noticeKey;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
