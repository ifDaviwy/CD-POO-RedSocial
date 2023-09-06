package com.campusdual.la_red_social;

import java.util.*;

public class User {
    private String name;
    private List<User> following;
    private List<Post> posts;

    public User(String name) {
        this.name = name;
        this.following = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    public void follow(User user) {
        if (this.following.contains(user)) {
            System.out.println("Ya estás siguiendo al usuario");
        } else {
            this.following.add(user);
            System.out.println("Ahora estas siguiendo a " + user.getName());
        }
    }

    public void unfollow(User user) {
        following.remove(user);
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }

    public String getName() {
        return name;
    }

    public List<User> getFollowing() {
        return following;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Post findPostById(int id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    public void commentOnPost(Post post, String commentContent) {
        Comment newComment = new Comment(commentContent, this);
        post.addComment(newComment);
    }

    public boolean deletePostById(int postId) {
        Iterator<Post> it = posts.iterator();
        while (it.hasNext()) {
            Post post = it.next();
            if (post.getId() == postId) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public boolean deleteCommentById(int commentId) {
        for (Post post : posts) {
            if (post.deleteCommentById(commentId)) {
                return true;
            }
        }
        return false;
    }

    public void showWall() {
        List<Post> allPosts = new ArrayList<>();

        for (User followed : this.following) {
            allPosts.addAll(followed.getPosts());
        }

        allPosts.sort(Comparator.comparingInt(Post::getId));

        System.out.println("*--- Muro ---*");
        for (int i = 0; i < Math.min(10, allPosts.size()); i++) {
            Post post = allPosts.get(i);
            System.out.println("Post ID: " + post.getId());
            System.out.println("De: " + post.getOwner().getName());
            System.out.println("Fecha: " + post.getDate());
            System.out.println("Contenido: " + post.getContentDetails());

            // Zona de comentarios
            List<Comment> comments = post.getComments();
            if (!comments.isEmpty()) {
                System.out.println("  Comentarios:");
                for (Comment comment : comments) {
                    System.out.println("    Comentario ID: " + comment.getId());
                    System.out.println("    De: " + comment.getOwner().getName());
                    System.out.println("    Fecha: " + comment.getDate());
                    System.out.println("    Contenido: " + comment.getText());
                    System.out.println("---");
                    System.out.println("");
                }
            }
            System.out.println("--------------------------");
        }
    }

    public List<User> suggestFriends(List<User> allUsers) {
        List<User> currentFriends = this.getFollowing();
        List<User> suggestedFriends = new ArrayList<>();

        for (User user : allUsers) {
            if (!user.equals(this) && !currentFriends.contains(user) && isFollowedByAnyFriend(user, currentFriends)) {
                suggestedFriends.add(user);
            }
        }

        return suggestedFriends;
    }

    private boolean isFollowedByAnyFriend(User user, List<User> currentFriends) {
        for (User friend : currentFriends) {
            if (friend.getFollowing().contains(user)) {
                return true;
            }
        }
        return false;
    }
}

