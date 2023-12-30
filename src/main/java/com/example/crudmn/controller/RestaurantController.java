package com.example.crudmn.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/resto")
@CrossOrigin("*")
public class RestaurantController {


    @GetMapping("/")
    public ResponseEntity<?> getResto()
    {

         try {

             InetAddress  inetAddress = InetAddress.getLocalHost();

             Map<String, String> mapaddres = new HashMap<>();

             mapaddres.put("connishostname",inetAddress.getCanonicalHostName());
             mapaddres.put("hostname",inetAddress.getHostName());
             mapaddres.put("hostaddress", inetAddress.getHostAddress());
             mapaddres.put("address",inetAddress.getAddress().toString());
            return  ResponseEntity.ok(mapaddres);
         }catch (Exception ex){
                  return  ResponseEntity.badRequest().body(ex.toString());

         }
    }
}
