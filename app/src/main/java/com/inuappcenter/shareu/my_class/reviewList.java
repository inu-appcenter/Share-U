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

    public reviewList(String uploadDate, String uploadId, String review, float score, int reviewKey) {
        this.uploadDate = uploadDate;
        this.uploadId = uploadId;
        this.review = review;
        this.score = score;
        this.reviewKey = reviewKey;
    }

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
