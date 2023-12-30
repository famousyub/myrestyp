package com.example.crudmn.entity;

import com.example.crudmn.entity.audit.DateAudit;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "commentblog")
public class Commentblog extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentblog_id")
    private Long id;

    public Commentblog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Userpoll getUser() {
        return user;
    }

    public void setUser(Userpoll user) {
        this.user = user;
    }

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;



    @ManyToOne
    @JoinColumn(name = "blog_id", referencedColumnName = "blog_id", nullable = false)
    @NotNull
    private Blog blog;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private Userpoll user;
}
