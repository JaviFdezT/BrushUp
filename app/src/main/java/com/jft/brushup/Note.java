package com.jft.brushup;

public class Note {

    private String title;
    private String details;

    public Note(){}

    public Note(String title, String details) {
        super();
        this.title = title;
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
