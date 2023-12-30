package com.example.crudmn.models;

import com.example.crudmn.entity.audit.DateAudit;
import com.example.crudmn.entity.audit.UserDateAudit;

import javax.persistence.*;

@Entity
@Table(name = "announcementsmodel")
public class AnnouncementModel extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "heading")
    private String heading;
    @Column(name = "body")
    private String body;

    public AnnouncementModel() {

    }

    public AnnouncementModel(long id, String heading, String body) {
        this.id = id;
        this.heading = heading;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}