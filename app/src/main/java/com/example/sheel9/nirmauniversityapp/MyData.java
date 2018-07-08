package com.example.sheel9.nirmauniversityapp;

/**
 * Created by Nitin on 25-03-2017.
 */
public class MyData {
    private int id;
    private String description,image_link;

    public MyData(int id, String image_link, String description) {
        this.id = id;
        this.description = description;
        this.image_link = image_link;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDecription(String description) {
        this.description = description;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }
}
