package com.inuappcenter.shareu.model;

import android.graphics.drawable.Drawable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.drawerlayout.widget.DrawerLayout;

public class Major {
    @SerializedName("majorName")
    @Expose
    public String first;
    @SerializedName("majorInitiality")
    @Expose
    public String third;

    public int viewType;
    @SerializedName("majorProName")
    @Expose
    public String second;

    public int line;
    //우선 전공에서는 second는 null, 전공 교양에서는 교수님 이름이 들어갈 것



    public Major(String first, String second,String third, int viewType,int line) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.viewType = viewType;
        this.line=line;
    }

    public String getFirst() {
        return first;
    }
    public String getSecond() {
        return second;
    }
    public String getThird() {
        return third;
    }
    public int getViewType() {
        return viewType;
    }
    public int getLine(){return line;}
}
