package com.example.crudmn.helpers;

public class UserData {

    private String email ;
    private String username ;
    private String name ;

    private Long id ;

    public String getEmail() {
        return email;
    }

    public UserData() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
