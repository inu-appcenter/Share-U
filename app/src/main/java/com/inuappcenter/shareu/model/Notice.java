package com.inuappcenter.shareu.model;

import android.view.View;

public class Notice {
    String title;
    int image;
    public Notice(String title,int image) {
        this.title = title;
        this.image=image;
    }
    public int getImage() {
        return image;
    }
    public String getTitle() {
        return title;
    }

}
