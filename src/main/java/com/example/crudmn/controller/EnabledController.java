package com.example.crudmn.controller;


import com.example.crudmn.models.User;
import com.example.crudmn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/enable")
@RestController
@CrossOrigin("*")
public class EnabledController {


    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<?> saver()
    {
        return  ResponseEntity.ok().body("147852369az");
    }

    @PostMapping("/enabled")
    public ResponseEntity<?> enabelUser(Authentication authentication)
    {
        UserDetails userDetails =(UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername()).get();

        user.setEnabled(true);

        userRepository.save(user);

        return ResponseEntity.ok().body("enabled user success");
    }
}
