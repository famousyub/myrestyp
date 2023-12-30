package com.example.crudmn.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "user_config")
public class Config {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id ;


    @Column(name = "config_name")
    private String configname;


    private Boolean isconfig;

    public Config() {
    }

    public Config(Long id, String configname, Boolean isconfig, String hashcode, String salt, Integer shows) {
        this.id = id;
        this.configname = configname;
        this.isconfig = isconfig;
        this.hashcode = hashcode;
        this.salt = salt;
        this.shows = shows;
    }

    @JsonIgnore
    private String hashcode ;


    private String salt ;

    private Integer shows ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfigname() {
        return configname;
    }

    public void setConfigname(String configname) {
        this.configname = configname;
    }

    public Boolean getIsconfig() {
        return isconfig;
    }

    public void setIsconfig(Boolean isconfig) {
        this.isconfig = isconfig;
    }

    public String getHashcode() {
        return hashcode;
    }

    public void setHashcode(String hashcode) {
        this.hashcode = hashcode;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getShows() {
        return shows;
    }

    public void setShows(Integer shows) {
        this.shows = shows;
    }
}
