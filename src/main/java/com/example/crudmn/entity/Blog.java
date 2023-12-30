package com.example.crudmn.entity;

import com.example.crudmn.entity.audit.DateAudit;
import com.example.crudmn.entity.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity
@Table(name = "posty")

public class Blog  extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private Long id ;

    @Column(name = "title", nullable = false)
    @Length(min = 1)
    private String title;



    @CreatedBy
    @Column(name = "created_by2", length = 50, updatable = false)
    private String createdBy2;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Blog() {
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

    public Userpoll getUser() {
        return user;
    }

    public void setUser(Userpoll user) {
        this.user = user;
    }

    public Set<Commentblog> getCommentsblog() {
        return commentsblog;
    }

    public void setCommentsblog(Set<Commentblog> commentsblog) {
        this.commentsblog = commentsblog;
    }

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private Userpoll user;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.REMOVE)
    private Set<Commentblog> commentsblog;


}
