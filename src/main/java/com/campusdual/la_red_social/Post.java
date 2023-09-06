package com.campusdual.la_red_social;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Post {
    private User owner;
    private Date date;
    private List<Comment> comments;
    private int id;
    private static int idCounter = 0;

    public Post(User owner) {
        this.id = ++idCounter;
        this.owner = owner;
        this.date = new Date();
        this.comments = new ArrayList<>();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public int numberOfComments() {
        return comments.size();
    }

    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Date getDate() {
        return this.date;
    }

    public int getId() {
        return id;
    }

    public String getContentDetails() {
        return "Post Generico";
    }

    public boolean deleteCommentById(int commentId) {
        Iterator<Comment> it = comments.iterator();
        while (it.hasNext()) {
            Comment comment = it.next();
            if (comment.getId() == commentId) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public static class TextPost extends Post {
        private String content;

        public TextPost(User owner, String content) {
            super(owner);
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        @Override
        public String getContentDetails() {
            return "Texto: " + this.content;
        }

    }

    public static class ImagePost extends Post {
        private String dimensions;
        private String title;

        public ImagePost(User owner, String dimensions, String title) {
            super(owner);
            this.dimensions = dimensions;
            this.title = title;
        }

        public String getDimensions() {
            return dimensions;
        }

        @Override
        public String getContentDetails() {
            return "Título de la imagen: " + this.title + " Dimensiones de la imagen: " + this.dimensions;
        }
    }

    public static class VideoPost extends Post {
        private String quality;
        private int duration;
        private String title;

        public VideoPost(User owner, String quality, int duration, String title) {
            super(owner);
            this.quality = quality;
            this.duration = duration;
            this.title = title;
        }

        public String getQuality() {
            return quality;
        }

        public int getDuration() {
            return duration;
        }

        @Override
        public String getContentDetails() {
            return "Calidad del video: " + this.quality + ", Duración: " + this.duration + ", Título: " + this.title;
        }
    }

}
