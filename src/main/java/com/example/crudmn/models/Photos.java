package com.example.crudmn.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("photosuser")
public class Photos {

    @Id
    private String id;


    private String imageName ;
    private String imageUrl;

    private byte[] imageload ;

    public Photos() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public byte[] getImageload() {
        return imageload;
    }

    public void setImageload(byte[] imageload) {
        this.imageload = imageload;
    }
}
