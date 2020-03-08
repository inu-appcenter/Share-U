package com.inuappcenter.shareu.my_class;

import com.google.gson.annotations.SerializedName;

public class userPointList {

    @SerializedName("point")
    int point;
    @SerializedName("documentKey")
    int documentKey;
    @SerializedName("pointloadDate")
    String pointloadDate;
    @SerializedName("title")
    String title;

    public userPointList(int point, int documentKey, String pointloadDate, String title) {
        this.point = point;
        this.documentKey = documentKey;
        this.pointloadDate = pointloadDate;
        this.title = title;
    }

    public int getPoint() {
        return point;
    }

    public int getDocumentKey() {
        return documentKey;
    }

    public String getPointloadDate() {
        return pointloadDate;
    }

    public String getTitle() {
        return title;
    }
}
