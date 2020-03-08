package com.inuappcenter.shareu.my_class;

import com.google.gson.annotations.SerializedName;

public class categoryCulture {


    @SerializedName("subjectName")
    String subjectname;

    @SerializedName("subjectInitiality")
    String subjectInitiality;

    @SerializedName("profName")
    String profName;

    public int viewType;
    public int line;

    public categoryCulture(String subjectname, String subjectInitiality, String profName, int viewType, int line) {
        this.subjectname = subjectname;
        this.subjectInitiality = subjectInitiality;
        this.profName = profName;
        this.viewType = viewType;
        this.line = line;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public String getSubjectInitiality() {
        return subjectInitiality;
    }

    public String getProfName() {
        return profName;
    }

    public int getViewType() {
        return viewType;
    }

    public int getLine() {
        return line;
    }
}
