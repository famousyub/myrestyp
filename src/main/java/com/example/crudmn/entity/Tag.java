package com.example.crudmn.entity;


import com.example.crudmn.entity.audit.DateAudit;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tag  extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    public Long getId() {
        return id;
    }

    public Tag() {
    }

    public Tag(Long id, String name, String description, List<Post> posts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.posts = posts;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}

