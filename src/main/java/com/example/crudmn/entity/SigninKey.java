package com.example.crudmn.entity;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.SignatureAlgorithm;
public class SigninKey {

    private static SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private static Key signingKey = new SecretKeySpec(
            DatatypeConverter.parseBase64Binary(System.getenv("SESSION_SECRET")), signatureAlgorithm.getJcaName());

    public static Key getSigningKey() {
        return signingKey;
    }
}
