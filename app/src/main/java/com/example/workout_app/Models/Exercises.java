package com.example.workout_app.Models;

public class Exercises {

    private String id;
    private String name;
    private String level;
    private String type;
    private String image;
    private String url;

    public Exercises(String id, String name, String level, String type, String image, String url) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.type = type;
        this.image = image;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
