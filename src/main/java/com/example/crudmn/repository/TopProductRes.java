package com.example.crudmn.repository;

import lombok.Value;

@Value
public class TopProductRes {

    String _id, name, image;
    Double price;
}