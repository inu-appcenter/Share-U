package com.inuappcenter.shareu.my_class;

public class MyUpload {
    String extension;
    String date;
    String title;

    public MyUpload(String extension, String date, String title) {
        this.extension = extension;
        this.date = date;
        this.title = title;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
