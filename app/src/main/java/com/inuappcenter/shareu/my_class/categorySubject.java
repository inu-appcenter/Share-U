package com.inuappcenter.shareu.my_class;

import com.google.gson.annotations.SerializedName;

public class categorySubject {
    @SerializedName("majorName")
    String majorName;
    @SerializedName("majorInitiality")
    String subjectName;

    public categorySubject(String majorName, String subjectName) {
        this.majorName = majorName;
        this.subjectName = subjectName;
    }

    public String getMajorName() {
        return majorName;
    }

    public String getSubjectName() {
        return subjectName;
    }
}
