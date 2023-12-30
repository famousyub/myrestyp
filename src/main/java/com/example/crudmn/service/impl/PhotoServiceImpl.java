package com.example.crudmn.service.impl;

import com.example.crudmn.models.Photos;
import com.example.crudmn.models.User;
import com.example.crudmn.payload.request.PhotoRequest;
import com.example.crudmn.payload.response.PhotosResponse;
import com.example.crudmn.repository.PhotosRepository;
import com.example.crudmn.repository.UserRepository;
import com.example.crudmn.service.UserPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class PhotoServiceImpl implements UserPhoto {


    @Autowired
    private PhotosRepository  phoRepo;

    @Autowired
    private UserRepository userRepository;
    @Override
    public PhotosResponse addPhoto(Authentication auth, PhotoRequest ph) {
        UserDetails me_ =(UserDetails) auth.getPrincipal();

        User me =  userRepository.findByUsername(me_.getUsername()).get();

        List<Photos> ph_  = new ArrayList<>();
        Photos pho = new Photos();

        pho.setImageload(ph.getPhoto());
        pho.setImageName(me.getUsername()+" " + new Date().toString());
        pho.setImageUrl("the me" +  me.getEmail() +" "  + new Date().toString());
        ph_.add(pho);
        phoRepo.save(pho);
        me.setPhotos(ph_);
        userRepository.save(me);
        return new PhotosResponse(pho.getImageName(),pho.getImageUrl());

    }

    @Override
    public boolean deletePhoto(Authentication auth, String id) {
          Photos ph_ = phoRepo.findById(id).get();

           phoRepo.delete(ph_);

           return true;

    }
}
