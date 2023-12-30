package com.example.crudmn.payload;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyUserResponse {

    private  String username ;
    private String email ;
    private String phonenumber ;

}
