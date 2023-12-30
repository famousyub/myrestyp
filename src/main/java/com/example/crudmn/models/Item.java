package com.example.crudmn.models;

import com.example.crudmn.entity.audit.DateAudit;
import com.example.crudmn.entity.audit.UserDateAudit;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item  extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column(name = "title")
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}