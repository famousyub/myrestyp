package com.example.crudmn.entity;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name="messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Message() {
    }

    public Message(int id, Userpoll sender, Userpoll recipient, Date date, Blob image, String text) {
        this.id = id;
        this.sender = sender;
        this.recipient = recipient;
        this.date = date;
        this.image = image;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Userpoll getSender() {
        return sender;
    }

    public void setSender(Userpoll sender) {
        this.sender = sender;
    }

    public Userpoll getRecipient() {
        return recipient;
    }

    public void setRecipient(Userpoll recipient) {
        this.recipient = recipient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Userpoll sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Userpoll recipient;

    @Column(name="message_date")
    private Date date;

    private Blob image;
    private String text;
}
