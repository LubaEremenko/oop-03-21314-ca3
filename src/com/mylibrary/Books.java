package com.mylibrary;

public class Books {
    private String title;
    private String author;
    private String summary;
    private String status;


    public Books(String title, String author, String summary, String status) {
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
