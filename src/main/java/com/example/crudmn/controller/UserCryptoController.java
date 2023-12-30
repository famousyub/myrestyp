package com.example.crudmn.controller;


import com.example.crudmn.entity.UserDetail;
import com.example.crudmn.exception.ApiExceptionHandler;
import com.example.crudmn.mapper.UserCryptoMapper;
import com.example.crudmn.models.User;
import com.example.crudmn.models.UserCryptoType;
import com.example.crudmn.repository.UserRepository;
import com.example.crudmn.service.UserCryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/file")
public class UserCryptoController {

    @Autowired
    private UserCryptoService userCryptoService;

    @Autowired
    private UserRepository userRepository;

    private  byte[] myfile;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException {
        myfile =file.getBytes();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addcrypto")
    public  ResponseEntity<?>  adcrypto(UserCryptoMapper userCryptoMapper, Authentication me){

        UserDetail me_ = (UserDetail) me.getPrincipal();

        User user = userRepository.findByEmail(me_.getEmailID()).get();



        userCryptoService.addUserCrypto(userCryptoMapper,user.getId(),myfile);

        return  ResponseEntity.ok().body("user added succesfully");

    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> download(@PathVariable String id) throws IOException {
        UserCryptoType  loadFile = userCryptoService.getUser(id);

        return ResponseEntity.ok()

                .body(loadFile);
    }

}
