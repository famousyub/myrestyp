package com.example.crudmn.entity;

import com.example.crudmn.entity.audit.UserDateAudit;
import io.jsonwebtoken.Jwts;



import javax.persistence.*;
import java.security.Key;
import java.util.Date;
import java.util.Random;
import java.util.UUID;


import java.security.Key;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



import io.jsonwebtoken.Jwts;



@Entity
@Table(name = "sessions")
public class Session  extends UserDateAudit {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;


    @Column(unique = true)
    private String payload;

    public Session() {
    }

    public Session(String payload) {
        this.payload = payload;
    }

    public Session(UUID id, String payload) {
        this.id = id;
        this.payload = payload;
    }

    public String createPayload(UUID userId) {
       // final Key signingKey = SigningKeyResolver();
       //String key = "login" + new Random(100000).toString() + new Date().toString();

        final Key signingKey = SigninKey.getSigningKey();
       //Key _u = (Key) key;
       // final String jws = Jwts.builder().setSubject("cashenvelope").claim("userId", userId).signWith(signingKey).compact();
      //  final String jws = Jwts.builder().setSubject("cashenvelope").claim("userId", userId).signWith(signingKey).compact();

        String jws = new Date(). toString() + signingKey.toString() + "me" + userId;
        this.setPayload(jws);

        return jws;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
