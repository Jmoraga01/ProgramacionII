package com.ugb.controlesbasicos;

import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private String description;
    private int imageResource;
    private boolean liked;

    public Person(String name, String description, int imageResource) {
        this.name = name;
        this.description = description;
        this.imageResource = imageResource;
        this.liked = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResource() {
        return imageResource;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}

