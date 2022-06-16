package com.example.workout_app.Models;

public class Dish {
    private String id;
    private String title;
    private String description;
    private String thumnailUrl;
    private String sourceUrl;

    public Dish(String id, String title, String description, String thumnailUrl, String sourceUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.thumnailUrl= thumnailUrl;
        this.sourceUrl = sourceUrl;
    }

    public Dish(String title, String description, String imgUrl, String source) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumnailUrl() {
        return thumnailUrl;
    }

    public void setThumnailUrl(String thumnailUrl) {
        this.thumnailUrl = thumnailUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
