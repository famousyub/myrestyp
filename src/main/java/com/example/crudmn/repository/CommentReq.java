package com.example.crudmn.repository;

import lombok.Data;

@Data
public class CommentReq {

    private Double rating;
    private String comment;
}