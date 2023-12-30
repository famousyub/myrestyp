package com.example.crudmn.dto;

import com.example.crudmn.entity.Commentblog;

public class CommentblogDto {

    private Long id;
    private String userName;
    private String body;
    private Long postId;
    private Long userId;

    public CommentblogDto() {
    }

    public  CommentblogDto(Commentblog comm){

        this.userName = comm.getUser().getUsername();
        this.body = comm.getBody();
        this.postId = comm.getBlog().getId();
        this.userId = comm.getUser().getId();


    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
