package com.inuappcenter.shareu.model;

public class SuperiorLecture {
    int image;
    String title;
    float rating;

    public int getImage() {
        return image;
    }

    public String getTitle()
   {
       return title;
   }

    public float getRating() {
        return rating;
    }
    public SuperiorLecture(int image, String title,float rating) {
        this.image = image;
        this.title = title;
        this.rating=rating;
    }
}
