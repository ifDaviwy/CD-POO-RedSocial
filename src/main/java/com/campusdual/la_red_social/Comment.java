package com.campusdual.la_red_social;

import java.util.Date;

public class Comment {
    private String text;
    private Date date;
    private User owner;
    private static int idCounter = 0;
    private int id;

    public Comment(String text, User owner) {
        this.id = ++idCounter;
        this.text = text;
        this.date = new Date();
        this.owner = owner;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public User getOwner() {
        return owner;
    }

    public int getId() {
        return id;
    }
}

