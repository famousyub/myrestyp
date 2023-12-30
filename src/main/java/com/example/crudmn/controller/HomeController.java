package com.example.crudmn.controller;


import com.example.crudmn.entity.Userpoll;
import com.example.crudmn.repository.UserPollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/home")
public class HomeController {


    @Autowired
    UserPollRepository userPollRepository;


    @GetMapping("")
    public ResponseEntity<?> home()
    {
        try {
            InetAddress inet =  InetAddress.getLocalHost();

            Map<String,String> uiet = new HashMap<>();
            uiet.put("timer", LocalDate.now().toString());
            uiet.put("dateserver", Instant.now().toString());

            uiet.put("ipi",inet.getCanonicalHostName());
            uiet.put("hostname",inet.getHostName());
            uiet.put("hostaddress",inet.getHostAddress());

            return  ResponseEntity.ok().body(uiet);
        }catch (Exception ec)
        {

            return ResponseEntity.badRequest().body(ec.toString());

        }
    }


    @GetMapping("/current")
    public ResponseEntity<?> cuureentHost(Authentication authentication , HttpServletRequest request)
    {


        UserDetails userDetails = (UserDetails)  authentication.getPrincipal();

        Userpoll userpoll = userPollRepository.findByUsername(userDetails.getUsername()).get();

        if(userpoll.equals(null)){
            return  ResponseEntity.badRequest().body("not user found should login");
        }


        String session =  request.getRequestedSessionId();

        Map<String,String> me= new HashMap<>();

        me.put("session-id",session);
        me.put("username",userpoll.getUsername());
        me.put("email",userpoll.getEmail());
        me.put("name",userpoll.getName());

        return  ResponseEntity.ok().body(me);




    }

    @GetMapping("/user/{username}/{userid}/{email}")
    public  ResponseEntity getusernameByEmailandName(@PathVariable("username") String username , @PathVariable("userid") Long userId, @PathVariable("email") String email)
    {

 Map<String,String> userMap = new HashMap<>();

 try {


             Userpoll u = userPollRepository.findByUsername(username).get();
             if(u.getEmail().toLowerCase().equals(email.toLowerCase())){
                 userMap.put("username",username);
                 userMap.put("user-id",userId.toString());
                 userMap.put("email",email);

             }
     return ResponseEntity.ok().body(userMap);

        }catch (Exception ex){
                 userMap.put("error",ex.toString());
                 return  ResponseEntity.ok().body(userMap);
        }
    }



}
