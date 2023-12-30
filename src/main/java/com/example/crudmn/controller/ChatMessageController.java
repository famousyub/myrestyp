package com.example.crudmn.controller;


import com.example.crudmn.models.Channel;
import com.example.crudmn.models.Message;
import com.example.crudmn.repository.ChannelRepository;
import com.example.crudmn.repository.MessageRepository;
import com.example.crudmn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/chat")
public class ChatMessageController {

    @Autowired
    ChannelRepository channelRepository;

    @GetMapping("/")
    public ResponseEntity<?> Chatte()
    {
        return ResponseEntity.ok().body("chatty");
    }
//    @Autowired
//    MessageRepository messageRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @PostMapping("/channels")
//    public ResponseEntity<?> postMessage (Authentication auth , @Valid @RequestBody Message message)
//    {
//        UserDetails them = (UserDetails) auth.getPrincipal();
//        message.setSender(them.getUsername());
//        return  ResponseEntity.ok().body("message send");
//
//    }
//
//    @PostMapping("/channels")
//    public ResponseEntity<Channel> createChannel(Authentication auth , @Valid @RequestBody Channel channel)
//    {
//        UserDetails theme = (UserDetails) auth.getPrincipal();
//        channel.setFirstUser(theme.getUsername());
//        return  ResponseEntity.ok().body( channelRepository.save(channel));
//    }
//
//    @GetMapping(value="/chats/stream" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<Message> streamMessage (@RequestParam String channelId)
//    {
//        return messageRepository.findWithTailableCursorByChannelId(channelId);
//    }

}
