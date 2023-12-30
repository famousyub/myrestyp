package com.example.crudmn.service;

import com.example.crudmn.models.Photos;
import com.example.crudmn.payload.request.PhotoRequest;
import com.example.crudmn.payload.response.PhotosResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;



public interface  UserPhoto {

     PhotosResponse addPhoto(Authentication auth, PhotoRequest ph);
     boolean  deletePhoto(Authentication auth , String id);
}
