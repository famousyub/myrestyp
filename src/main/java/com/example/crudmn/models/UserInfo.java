package com.example.crudmn.models;

public class UserInfo {


    private  String firstname;
    private String lastname ;

    private String contact ;


    private byte[] avater ;

    public UserInfo() {
    }

    public UserInfo(String firstname, String lastname, String contact, byte[] avater) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.contact = contact;
        this.avater = avater;
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

    public byte[] getAvater() {
        return avater;
    }

    public void setAvater(byte[] avater) {
        this.avater = avater;
    }
}
