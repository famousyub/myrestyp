package com.example.crudmn.controller;


import com.example.crudmn.models.User;
import com.example.crudmn.payload.response.MessageResponse;
import com.example.crudmn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/send")
@CrossOrigin("*")
public class SendingMailController {

    @Autowired
    UserRepository userRepository ;

    @GetMapping("/mail")
    public ResponseEntity<?> sendMail()
    {
        List<String> mails = new ArrayList<>();

        List<User> users = userRepository.findAll();

        for (User user: users
             ) {

            String _mail = new String(user.getEmail());
            mails.add(_mail);



        }
        return  ResponseEntity.ok().body(mails);
    }

    @GetMapping("/user")
    public  ResponseEntity<?> findMeOruser(@RequestParam("emailOrusername") String emailOrUsername)
    {
        User _userbyemail = userRepository.findByEmail(emailOrUsername).get();
        User _userbyname = userRepository.findByUsername(emailOrUsername).get();

        if(_userbyemail.equals(null) && _userbyname.equals(null)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error:not found !"));
        }
        else  {
            Map<String , String> me = new HashMap<>();

            me.put("email-user",_userbyemail.getEmail().equals(null)==false ? _userbyemail.getEmail():_userbyname.getEmail());
            return ResponseEntity.ok().body(me);
        }
    }
}
