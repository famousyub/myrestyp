package com.example.crudmn.controller;


import com.example.crudmn.models.User;
import com.example.crudmn.models.psn.DoubledObjectEntity;
import com.example.crudmn.models.psn.IdObjectEntity;
import com.example.crudmn.models.psn.PostEntity;
import com.example.crudmn.repository.UserRepository;
import com.example.crudmn.service.PostPsnService;
import com.example.crudmn.service.ResponseObjectService;
import com.example.crudmn.service.UserPsnService;
import com.example.crudmn.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class UserPsnController {

    @Autowired
    private UserPsnService userService;

    @Autowired
    private UserRepository userRepo;


    @PostMapping("/users")
    public ResponseEntity<ResponseObjectService> findAllUsers() {
        return new ResponseEntity<ResponseObjectService>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/users/profile")
    public ResponseEntity<ResponseObjectService> findById(@RequestBody IdObjectEntity inputId) {
        return new ResponseEntity<ResponseObjectService>(userService.findById(inputId.getId()), HttpStatus.OK);
    }

    @PostMapping("/users/follow")
    public ResponseEntity<ResponseObjectService> followUser(@RequestBody DoubledObjectEntity doubleId) {
        return new ResponseEntity<ResponseObjectService>(userService.followUser(doubleId), HttpStatus.OK);
    }

    @PostMapping("/users/unfollow")
    public ResponseEntity<ResponseObjectService> unfollowUser(@RequestBody DoubledObjectEntity doubleId) {
        return new ResponseEntity<ResponseObjectService>(userService.unfollowUser(doubleId), HttpStatus.OK);
    }

    @PostMapping("/users/getfollowing")
    public ResponseEntity<ResponseObjectService> findFollowing(@RequestBody IdObjectEntity inputId) {
        return new ResponseEntity<ResponseObjectService>(userService.findFollowing(inputId.getId()), HttpStatus.OK);
    }

    @PostMapping("/users/getfollower")
    public ResponseEntity<ResponseObjectService> findFollower(@RequestBody IdObjectEntity inputId) {
        return new ResponseEntity<ResponseObjectService>(userService.findFollower(inputId.getId()), HttpStatus.OK);
    }

    @PutMapping("/users/update")
    public ResponseEntity<ResponseObjectService> update(@RequestBody User inputUser) {
        return new ResponseEntity<ResponseObjectService>(userService.update(inputUser), HttpStatus.OK);
    }

    @GetMapping("/getdata")
    public ResponseEntity<String> testAfterLogin(Principal p) {
        return ResponseEntity.ok("Welcome. You are: " + p.getName());
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/client-ip-address",
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    @ResponseBody
    public String getClientIPAddress(HttpServletRequest request) {
        String ip = HttpUtils.getRequestIP(request);
        return "Client IP Address: " + ip;
    }

}
