package com.inuappcenter.shareu.my_class;

import com.google.gson.annotations.SerializedName;

public class categoryResend {

    @SerializedName("majorName")
    String majorName;
    @SerializedName("subjectName")
    String subjectName;
    @SerializedName("profName")
    String profName;

    public String getMajorName() {
        return majorName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getProfName() {
        return profName;
    }
}
