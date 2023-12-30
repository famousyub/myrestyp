package com.example.crudmn.payload.request;

public class SimpleUserInfo {
    private String firstname;
    private String lastname ;

    private String contact;

    public String getFirstname() {
        return firstname;
    }

    public SimpleUserInfo() {
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
