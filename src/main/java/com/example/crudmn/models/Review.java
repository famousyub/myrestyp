package com.example.crudmn.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
public  class Review {

    @MongoId
    private String _id;
    private String name;
    private Double rating = 0.0;
    private String comment;
    private String user;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

    public Review() {
        this._id = ObjectId.get().toHexString();
        this.setCreatedAt(Instant.now());
    }
}

