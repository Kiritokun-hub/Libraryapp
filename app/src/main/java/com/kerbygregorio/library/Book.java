package com.kerbygregorio.library;

public class Book {
    private int id;
    private String name;
    private String authors;
    private String description;
    private int imageResourceId;

    public Book(String name, String authors, String description) {
        this.name = name;
        this.authors = authors;
        this.description = description;
    }

    public String getTitle() {
        return name;
    }

    public String getAuthors() {
        return authors;
    }

    public String getDescription() {
        return description;
    }


    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }


    public int getImageResourceId() {
        return imageResourceId;
    }


    public String getName() {
        return name;
    }


}
