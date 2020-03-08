package com.inuappcenter.shareu.my_class;

import com.google.gson.annotations.SerializedName;

public class MyInform {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("major")
    private String major;
    @SerializedName("tel")
    private String tel;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public String getTel() {
        return tel;
    }
}
