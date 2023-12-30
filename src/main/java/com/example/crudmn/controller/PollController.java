package com.example.crudmn.controller;


import com.example.crudmn.entity.Poll;
import com.example.crudmn.entity.Userpoll;
import com.example.crudmn.payload.request.PollRequest;
import com.example.crudmn.payload.request.VoteRequest;
import com.example.crudmn.payload.response.ApiResponse;
import com.example.crudmn.payload.response.PagedResponse;
import com.example.crudmn.payload.response.PollResponse;
import com.example.crudmn.repository.PollRepository;
import com.example.crudmn.repository.UserPollRepository;
import com.example.crudmn.repository.VoteRepository;

import com.example.crudmn.security.services.UserDetailsImpl;
import com.example.crudmn.security.services.UserPrincipal;
import com.example.crudmn.service.PollService;
import com.example.crudmn.utils.AppContsants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/polls")
@CrossOrigin("*")
public class PollController {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserPollRepository userRepository;

    @Autowired
    private PollService pollService;


    @GetMapping
    public PagedResponse<PollResponse> getPolls(Authentication currentUser ,
                                                @RequestParam(value = "page", defaultValue = AppContsants.DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "size", defaultValue = AppContsants.DEFAULT_PAGE_SIZE) int size) {
        UserDetails u = (UserDetails) currentUser.getPrincipal();

        Userpoll userpoll = userRepository.findByUsername(u.getUsername()).get();

        UserDetailsImpl user_ = new UserDetailsImpl(userpoll.getId().toString(),userpoll.getUsername(),userpoll.getEmail());
       // UserPrincipal userPrincipal = new UserPrincipal(userpoll.getId(),userpoll.getUsername(),userpoll.getUsername(),userpoll.getEmail(),userpoll.getPassword());

        return pollService.getAllPolls(user_, page, size);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') && hasRole('ROLE_USER')")
    public ResponseEntity<?> createPoll(@Valid @RequestBody PollRequest pollRequest) {
        Poll poll = pollService.createPoll(pollRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{pollId}")
                .buildAndExpand(poll.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Poll Created Successfully"));
    }

    @GetMapping("/{pollId}")
    public PollResponse getPollById(Authentication currentUser,
                                    @PathVariable Long pollId) {
        UserDetails u = (UserDetails) currentUser.getPrincipal();

        UserPrincipal _i ;


        Userpoll userpoll = userRepository.findByUsername(u.getUsername()).get();

        UserDetailsImpl _i1 = new UserDetailsImpl(userpoll.getId().toString(), userpoll.getUsername(),userpoll.getEmail());

       // UserPrincipal userPrincipal = new UserPrincipal();
                //(userpoll.getId(),userpoll.getUsername(),userpoll.getUsername(),userpoll.getEmail(),userpoll.getPassword());

        //System.out.println(userPrincipal.getId());
        return pollService.getPollById(pollId, _i1);
    }

    @PostMapping("/{pollId}/votes")
    @PreAuthorize("hasRole('USER') && hasRole('ROLE_USER')")
    public PollResponse castVote(Authentication currentUser,
                                 @PathVariable Long pollId,
                                 @Valid @RequestBody VoteRequest voteRequest) {
        UserDetails u = (UserDetails) currentUser.getPrincipal();

        Userpoll userpoll = userRepository.findByUsername(u.getUsername()).get();

        UserDetailsImpl user_ = new UserDetailsImpl(userpoll.getId().toString(),userpoll.getUsername(),userpoll.getEmail());
        //UserPrincipal userPrincipal = new UserPrincipal(userpoll.getId(),userpoll.getUsername(),userpoll.getUsername(),userpoll.getEmail(),userpoll.getPassword());

        return pollService.castVoteAndGetUpdatedPoll(pollId, voteRequest, user_);
    }

}
