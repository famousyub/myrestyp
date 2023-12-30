package com.example.crudmn.entity;

import com.example.crudmn.entity.audit.UserDateAudit;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "jobs")

public class Job extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    public Long getId() {
        return id;
    }

    public Job(Long id, String title, List<Userpoll> users) {
        this.id = id;
        this.title = title;
        this.users = users;
    }

    public Job() {
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

    public List<Userpoll> getUsers() {
        return users;
    }

    public void setUsers(List<Userpoll> users) {
        this.users = users;
    }

    @ManyToMany(mappedBy = "jobs")
    private List<Userpoll> users = new ArrayList<>();



}
