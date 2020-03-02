package com.inuappcenter.shareu.my_class;

import com.google.gson.annotations.SerializedName;

public class reviewList {
    @SerializedName("uploadDate")
    String uploadDate;
    @SerializedName("uploadId")
    String uploadId;
    @SerializedName("review")
    String review;
    @SerializedName("score")
    float score;
    @SerializedName("reviewKey")
    int reviewKey;

    public String getUploadDate() {
        return uploadDate;
    }

    public String getUploadId() {
        return uploadId;
    }

    public String getReview() {
        return review;
    }

    public float getScore() {
        return score;
    }

    public int getReviewKey() {
        return reviewKey;
    }
}
