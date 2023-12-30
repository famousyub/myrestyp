package com.example.crudmn.dto;

import com.example.crudmn.entity.Blog;

public class BlogDto {

    private Long id;
    private String title;
    private String body;
    private Long userId;
    private String userName;
    private String createdBy;

    public BlogDto() {
    }


    public BlogDto(Blog blog) {
        this.title = blog.getTitle();
        this.body = blog.getBody();
        this.userId= blog.getUser().getId();
        this.userName= blog.getUser().getUsername();
       // this.createdBy= blog.getCreatedBy();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
