package com.example.crudmn.controller;


import com.example.crudmn.entity.Song;
import com.example.crudmn.entity.Userpoll;
import com.example.crudmn.helpers.UserData;
import com.example.crudmn.repository.SongRepository;
import com.example.crudmn.repository.UserPollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/store")
@RestController
@CrossOrigin("*")
public class StoreController {

    @Autowired
    UserPollRepository userPollRepository;
    @Autowired
    private SongRepository songRepository;


    @GetMapping("/songs")
    public ResponseEntity<?> allsongs()
    {
        List<Song> songs =(List<Song>) songRepository.findAll();
        return ResponseEntity.ok().body(songs);
    }

    @GetMapping("/users")
    public ResponseEntity<?> allUsers()
    {
        List<UserData> userData =new ArrayList<>();
        List<Userpoll> userpolls = userPollRepository.findAll();

        for (Userpoll user:userpolls
             ) {
            UserData userData1 =new UserData();
            userData1.setEmail(user.getEmail());
            userData1.setName(user.getName());
            userData1.setUsername(user.getUsername());
            userData1.setId(user.getId());

            userData.add(userData1);

        }
return ResponseEntity.ok().body(userData);

    }

    @GetMapping("/user")
    public  ResponseEntity<?> searchUser(@RequestParam("username") String username)
    {

        try {
            Userpoll user =userPollRepository.findByUsername(username).get();

            UserData userData1 =new UserData();
            userData1.setEmail(user.getEmail());
            userData1.setName(user.getName());
            userData1.setUsername(user.getUsername());
            userData1.setId(user.getId());

            return  ResponseEntity.ok().body(userData1);

        }catch (Exception ex)
        {
            return  ResponseEntity.badRequest().body(ex.toString());
        }
    }

}
