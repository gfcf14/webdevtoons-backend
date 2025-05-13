package com.gfcf14.webdevtoons.model;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @Column(name = "date") // formatted as YYYY-MM-DD
    private String date;

    private String title;
    private String description;
    private String image; // a url

    public Post() {}

    public Post(String date, String title, String description, String image) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
