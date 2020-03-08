package com.inuappcenter.shareu.my_class;

import com.google.gson.annotations.SerializedName;

public class RestError {
    @SerializedName("ans")
    private String errorDetails;

    public String getErrorDetails() {
        return errorDetails;
    }
}
