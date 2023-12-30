package com.example.crudmn.helpers;

public class TheRestoclient {


    private String token_ ;
    private String username;


    private Integer status ;

    public TheRestoclient() {
    }

    public String getToken_() {
        return token_;
    }

    public void setToken_(String token_) {
        this.token_ = token_;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
