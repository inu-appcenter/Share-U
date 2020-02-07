package com.inuappcenter.shareu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class profName {
    @SerializedName("profName")
    @Expose
    private String profName;

    public String getProfName() {
        return profName;
    }

    public profName(String profName) {
        this.profName = profName;
    }
}