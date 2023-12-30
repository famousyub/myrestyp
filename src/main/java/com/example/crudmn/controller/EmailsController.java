package com.example.crudmn.controller;

import com.example.crudmn.models.User;
import com.example.crudmn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mailing")
@CrossOrigin("*")
public class EmailsController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/me/{email}")
    public ResponseEntity<?> getMeMail(@PathVariable("email") String email)
    {
        try {
            User _me = userRepo.findByEmail(email).get();

            return ResponseEntity.ok().body(_me);

        }catch (Exception ex){
            return ResponseEntity.ok().body(ex.toString());
        }


    }


}
