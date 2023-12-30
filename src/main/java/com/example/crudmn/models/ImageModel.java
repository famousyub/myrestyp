package com.example.crudmn.models;

import com.example.crudmn.entity.audit.UserDateAudit;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="images")
public class ImageModel extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   // @GeneratedValue(generator = "uuid")
    //@GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String description;
    private String tutorName;
    private String image;

    public ImageModel() {

    }

    public ImageModel(String id, String name, String description, String tutorName, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tutorName = tutorName;
        this.image = image;
    }

    public ImageModel(String name, String description, String tutorName,String image) {
        this.name = name;
        this.description = description;
        this.tutorName = tutorName;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String name) {
        this.image = name;
    }
}
