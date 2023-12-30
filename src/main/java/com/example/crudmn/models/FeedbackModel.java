package com.example.crudmn.models;

import com.example.crudmn.entity.audit.DateAudit;
import com.example.crudmn.entity.audit.UserDateAudit;

import javax.persistence.*;

@Entity
@Table(name = "feedback")
public class FeedbackModel extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "message")
    private String message;

    public FeedbackModel (){

    }

    public FeedbackModel(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}