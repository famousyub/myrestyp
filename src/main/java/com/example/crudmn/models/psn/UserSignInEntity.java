package com.example.crudmn.models.psn;

public class UserSignInEntity {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public UserSignInEntity() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
