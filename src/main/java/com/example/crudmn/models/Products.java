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
@Document(collection = "products")
public class Products {

    @Id
    private String _id;
    @NotBlank
    private String name;
    @NotBlank
    private String image;
    @NotBlank
    private String brand;
    @NotBlank
    private String description;
    @NotBlank
    private String category;
    @NotBlank
    private Integer numReviews = 0;
    @NotBlank
    private Double price = 0.0;
    @NotBlank
    private Integer countInStock = 0;

    private List<Review> reviews = new ArrayList();
    @NotBlank
    private Double rating = 0.0;
    @NotBlank
    private String user;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;
}
