package com.example.crudmn.models.psn;

import com.example.crudmn.models.User;

public class PostByFollowing {

    private User user;

    private PostEntity post;

    public PostByFollowing() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

    public PostByFollowing(User user, PostEntity post) {
        this.user = user;
        this.post = post;
    }
}
