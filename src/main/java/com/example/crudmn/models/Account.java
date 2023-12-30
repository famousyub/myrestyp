package com.example.crudmn.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "useraccounts")
public class Account {

    private int accNo;
    private String accName;
    private double amount;
    private String currency;

    @Id
    private String id;

    public Account() {
    }

    public Account(int accNo, String accName, double amount, String currency, String id) {
        this.accNo = accNo;
        this.accName = accName;
        this.amount = amount;
        this.currency = currency;
        this.id = id;
    }

    public int getAccNo() {
        return accNo;
    }

    public void setAccNo(int accNo) {
        this.accNo = accNo;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
