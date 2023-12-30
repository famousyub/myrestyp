package com.example.crudmn.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Document("usercryptotype")
@AllArgsConstructor
@NoArgsConstructor
public class UserCryptoType {

    @Id
    private String id;

    @NotBlank
    @Size(max = 20)
    private String displayName;

    private String userId;

    @NotBlank
    @Size(max = 50)
    @Email
    private String userType;

    private  String birthday;

    private String filename;
    private String fileType;
    private String fileSize;
    private byte[] file;
}
