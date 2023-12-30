package com.example.crudmn.controller;


import com.example.crudmn.entity.Userpoll;
import com.example.crudmn.payload.MyUserResponse;
import com.example.crudmn.repository.UserPollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v4/u")
@CrossOrigin("*")
public class MyClientController {

    @Autowired
    private UserPollRepository userPollRepository;


    @GetMapping("/{id}")
    public ResponseEntity<?> getUserByid(@PathVariable("id") Long user_id)
    {

        Userpoll userpoll = userPollRepository.findById(user_id).get();
        MyUserResponse myUserResponse = new MyUserResponse();
        myUserResponse.setEmail(userpoll.getEmail());
        myUserResponse.setUsername(userpoll.getUsername());
        myUserResponse.setPhonenumber(userpoll.getPhonenumber());

        return  ResponseEntity.ok().body(myUserResponse);
    }


    @GetMapping("/myemail")
    public  ResponseEntity<?> sendme(@RequestParam("data") String mail  , Authentication user_){

        UserDetails user = (UserDetails) user_.getPrincipal();
        Userpoll _userpoll = userPollRepository.findByUsername(user.getUsername()).get();
        MyUserResponse myUserResponse =new MyUserResponse();

        Map<String , MyUserResponse> usermail = new HashMap<>();

        myUserResponse.setPhonenumber(_userpoll.getPhonenumber());
        myUserResponse.setUsername(_userpoll.getUsername());
        myUserResponse.setEmail(_userpoll.getEmail());
        usermail.put("usermail" , myUserResponse);
        return  ResponseEntity.ok().body(usermail);

    }




}
