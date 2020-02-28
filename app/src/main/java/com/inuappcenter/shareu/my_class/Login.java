package com.inuappcenter.shareu.my_class;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.session.MediaSession;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

import static okhttp3.internal.Internal.instance;

public class Login {

    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }


}
