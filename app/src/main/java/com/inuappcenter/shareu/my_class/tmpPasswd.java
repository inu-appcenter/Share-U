package com.inuappcenter.shareu.my_class;

import com.google.gson.annotations.SerializedName;

public class tmpPasswd {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
