package com.example.crudmn.mapper;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
public class UserCryptoMapper {

    private String displayName;

    @NotBlank
    @Size(max = 50)
    @Email
    private String userType;

    private  String birthday;

    private String filename;
    private String fileType;
    private String fileSize;

}
