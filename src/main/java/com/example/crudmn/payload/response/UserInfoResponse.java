package com.example.crudmn.payload.response;

public class UserInfoResponse {

    private String firstname;
    private String lastname ;

    private String contact;

    public UserInfoResponse(String firstname, String lastname, String contact) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.contact = contact;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
