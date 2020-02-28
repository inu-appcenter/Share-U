package com.inuappcenter.shareu.my_class;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

public class TokenManager {
    private static final String PREFERENCE_NAME = "YEAH";
    private static final String TOKEN_KEY = "MY_AWESOME_KEY";

    private static TokenManager instance;

    public static TokenManager getInstance() {
        if (instance == null) {
            instance = new TokenManager();
        }

        return instance;
    }

    /**
     * Private constructor.
     */
    private TokenManager() { }


    private SharedPreferences getPreference(Context context) {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Put token into shared preferences.
     * Existing token will be overwritten.
     * @param context a context.
     * @param string token to put in.
     */
    public void putToken(Context context, @Nullable String string) {
        SharedPreferences prefs = getPreference(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(TOKEN_KEY, string);
        editor.apply();
    }

    /**
     * Get token from shared preferences.
     * @param context a context.
     * @return token if exists, otherwise null.
     */
    @Nullable
    public String getToken(Context context) {
        SharedPreferences prefs = getPreference(context);
        return prefs.getString(TOKEN_KEY, null);
    }
}
