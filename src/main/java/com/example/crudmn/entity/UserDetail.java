package com.example.crudmn.entity;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="USER_DETAILS")
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;

    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private String emailID;

    private LocalDate lastUpdated;

    public UserDetail(Long id, String username, String password, String firstname, String lastname, String emailID, LocalDate lastUpdated) {
        Id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailID = emailID;
        this.lastUpdated = lastUpdated;
    }

    public Long getId() {
        return Id;
    }

    public UserDetail(String username, String password, String firstname, String lastname, String emailID) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailID = emailID;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailID() {
        return emailID;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "Id=" + Id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", emailID='" + emailID + '\'' +
                ", lastUpdated=" + lastUpdated +
                '}';
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public UserDetail() {
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}