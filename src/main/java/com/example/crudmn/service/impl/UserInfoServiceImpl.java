package com.example.crudmn.service.impl;

import com.example.crudmn.models.User;
import com.example.crudmn.models.UserInfo;
import com.example.crudmn.payload.request.UserInfoRequest;
import com.example.crudmn.payload.response.UserInfoResponse;
import com.example.crudmn.repository.UserRepository;
import com.example.crudmn.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserRepository userRepository ;


    @Override
    public void showinfo() {

    }

    @Override
    public UserInfoResponse createUserInfo(Authentication auth, UserInfoRequest userinfo) {

        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User me =  userRepository.findByUsername(userDetails.getUsername()).get();


        UserInfo userinfo_ = new UserInfo();
        userinfo_.setFirstname(userinfo.getFirstname());
        userinfo_.setLastname(userinfo.getLastname());
        userinfo_.setContact(userinfo.getContact());
        userinfo_.setAvater(userinfo.getAvater());
        me.setUserinfo(userinfo_);

        userRepository.save(me);

        UserInfoResponse info = new UserInfoResponse(userinfo.getFirstname(),userinfo.getLastname(),userinfo.getContact());
        return  info;


    }
}
