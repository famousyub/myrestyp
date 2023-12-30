package com.example.crudmn.controller;


import com.example.crudmn.entity.UserDetail;
import com.example.crudmn.entity.Userpoll;
import com.example.crudmn.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pinger")
@CrossOrigin("*")
public class IpingerController {

    @Autowired
    private UserDetailRepository userDetailRepository ;


    @GetMapping("/user/orme/{user_id}")
    public ResponseEntity<?> getMeOruser(@PathVariable("user_id") Long user_id)
    {
        UserDetail user_ = userDetailRepository.findById(user_id).get();

        if(user_.equals(null)){
            return ResponseEntity.badRequest().body("error  something wrong");
        }


        Map<String,String> userorme = new HashMap<>();
        userorme.put("username",user_.getUsername());
        userorme.put("user_id",user_.getId().toString());
        userorme.put("email",user_.getEmailID());
        userorme.put("firstname",user_.getFirstname());
        userorme.put("lastname",user_.getFirstname());
        return ResponseEntity.ok().body(userorme);
    }


    @GetMapping("/ping")
    public ResponseEntity<?> getUsersIp(){

        HashMap<String, String> m =new HashMap<>();

        try {

            InetAddress ip = InetAddress.getLocalHost();
            String user_ip= ip.getCanonicalHostName();
            String con_ip = ip.getHostAddress();
            String con_adre =ip.getCanonicalHostName();
            m.put("ip", user_ip);
            m.put("con_ip", con_ip);
            m.put("con_adree", con_adre);

            return  ResponseEntity.ok().body(m);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.toString());

        }

    }

    @GetMapping("/ping/v6")
    public ResponseEntity<?> getUsersIpv6(){

        HashMap<String, String> m =new HashMap<>();

        try {

            InetAddress ip = InetAddress.getLocalHost();
            String user_ip= ip.getCanonicalHostName();
            String con_ip = ip.getHostAddress();
            String con_adre =ip.getCanonicalHostName();
            m.put("ip", user_ip);
            m.put("con_ip", con_ip);
            m.put("con_adree", con_adre);

            return  ResponseEntity.ok().body(m);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.toString());

        }

    }

}
