package com.example.crudmn.models.psn;

import com.example.crudmn.models.User;

public class AuthorizedEntity {

    private User user;

    private String token;

    public AuthorizedEntity() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthorizedEntity(User user, String token) {
        this.user = user;
        this.token = token;
    }
}
