package com.example.crudmn.models;

import lombok.Data;

@Data
public class PaymentResult {

    private String id;
    private String status;
    private String updateTime;
    private String emailAddress;
}