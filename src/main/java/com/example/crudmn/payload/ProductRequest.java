package com.example.crudmn.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class ProductRequest {

    private  String code ;
    private  String name ;

    private  double price ;

}
