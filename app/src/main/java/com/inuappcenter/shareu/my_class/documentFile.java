package com.inuappcenter.shareu.my_class;

import com.google.gson.annotations.SerializedName;

public class documentFile {

    @SerializedName("fileName")
    String fileName;
    @SerializedName("ans")
    boolean ans;

    public String getFileName() {
        return fileName;
    }

    public boolean getAns() {
        return ans;
    }
}
