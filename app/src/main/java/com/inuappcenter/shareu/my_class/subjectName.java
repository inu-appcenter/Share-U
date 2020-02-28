package com.inuappcenter.shareu.my_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class subjectName {
    @SerializedName("subjectName")
    private String majorName;


    public subjectName(String majorName) {
        this.majorName = majorName;
    }

    public String getMajorName() {
        return majorName;
    }

}
