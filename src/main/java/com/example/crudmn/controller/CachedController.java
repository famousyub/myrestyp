package com.example.crudmn.controller;


import com.example.crudmn.entity.UserDetail;
import com.example.crudmn.helpers.UserCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("/api/v5")
@CrossOrigin("*")
public class CachedController {


    @Value("${redis_server_app.url}")
    private String url;
    @PutMapping("/me")
    public ResponseEntity<?>  saveUserLogin(Authentication me, @RequestParam("passtoken") String passtoken)
    {
        RestTemplate restTemplate = new RestTemplate();
        UserDetails _me = (UserDetails) me.getPrincipal();
        UserCache userCache = new UserCache();

        userCache.setId( Math.abs (new Random().nextLong() * 10000));
        userCache.setToken(new Date().toString());
        userCache.setUsername(_me.getUsername());
        userCache.setName(LocalDate.now().toString());
        userCache.setFollowers(0);

        restTemplate.put(url, userCache, UserCache.class);

        //ResponseEntity<UserCache> response = HttpStatus.OK;
/*
// check response
		if (response.getStatusCode() == HttpStatus.OK) {
			System.out.println("Request Successful");
		} else {
			System.out.println("Request Failed");
		}
*/
        return ResponseEntity.ok().body(userCache);
    }
}
