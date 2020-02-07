package com.inuappcenter.shareu.model;

public class File {
    String title;
    String subjectName;
    String profName;
    String content;

    public File(String title, String subjectName, String profName, String content) {
        this.title = title;
        this.subjectName = subjectName;
        this.profName = profName;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getProfName() {
        return profName;
    }

    public String getContent() {
        return content;
    }
}
