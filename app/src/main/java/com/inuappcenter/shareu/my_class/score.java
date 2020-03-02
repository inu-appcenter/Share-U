package com.inuappcenter.shareu.my_class;

import com.google.gson.annotations.SerializedName;

public class score {

    @SerializedName("avg(score)")
    float score;

    public float getScore() {
        return score;
    }
}
