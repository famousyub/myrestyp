package com.example.crudmn.controller;

import com.example.crudmn.entity.Userpoll;
import com.example.crudmn.exception.ResourceNotFoundException;
import com.example.crudmn.payload.response.*;
import com.example.crudmn.repository.PollRepository;
import com.example.crudmn.repository.UserPollRepository;
import com.example.crudmn.repository.VoteRepository;
import com.example.crudmn.security.services.CurrentUser;
import com.example.crudmn.security.services.UserDetailsImpl;
import com.example.crudmn.security.services.UserPrincipal;
import com.example.crudmn.service.PollService;
import com.example.crudmn.utils.AppContsants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(value = "*",maxAge = 3600)
@RequestMapping("/api/v3")
public class UserPollController {


    @Autowired
    private UserPollRepository userRepository;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private PollService pollService;

    //private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER') && hasRole('ROLE_USER')")
    public UserSummary getCurrentUser(Authentication currentUser) {


         UserDetails u = (UserDetails) currentUser.getPrincipal();

         Userpoll userpoll = userRepository.findByUsername(u.getUsername()).get();
        UserSummary userSummary = new UserSummary(userpoll.getId(), userpoll.getUsername(), userpoll.getName());
        return userSummary;
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{username}")
    public UserPollProfile getUserProfile(@PathVariable(value = "username") String username) {
        Userpoll user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        long pollCount = pollRepository.countByCreatedBy(user.getId());
        long voteCount = voteRepository.countByUserId(user.getId());

        UserPollProfile userProfile = new UserPollProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt(), pollCount, voteCount);

        return userProfile;
    }

    @GetMapping("/users/{username}/polls")
    public PagedResponse<PollResponse> getPollsCreatedBy(@PathVariable(value = "username") String username,
                                                        Authentication currentUser,
                                                         @RequestParam(value = "page", defaultValue = AppContsants.DEFAULT_PAGE_NUMBER) int page,
                                                         @RequestParam(value = "size", defaultValue = AppContsants.DEFAULT_PAGE_SIZE) int size) {
        UserDetails u = (UserDetails) currentUser.getPrincipal();

        Userpoll userpoll = userRepository.findByUsername(u.getUsername()).get();

       // UserPrincipal userPrincipal = new UserPrincipal(userpoll.getId(),userpoll.getUsername(),userpoll.getUsername(),userpoll.getEmail(),userpoll.getPassword());

        UserDetailsImpl me_ = new UserDetailsImpl(userpoll.getId().toString(),userpoll.getUsername(),userpoll.getEmail());

        return pollService.getPollsCreatedBy(username, me_, page, size);
    }

    @GetMapping("/users/{username}/votes")
    public PagedResponse<PollResponse> getPollsVotedBy(@PathVariable(value = "username") String username,
                                                       Authentication  currentUser,
                                                       @RequestParam(value = "page", defaultValue = AppContsants.DEFAULT_PAGE_NUMBER) int page,
                                                       @RequestParam(value = "size", defaultValue = AppContsants.DEFAULT_PAGE_SIZE) int size) {
        UserDetails u = (UserDetails) currentUser.getPrincipal();

        Userpoll userpoll = userRepository.findByUsername(u.getUsername()).get();

       // UserPrincipal userPrincipal = new UserPrincipal(userpoll.getId(),userpoll.getUsername(),userpoll.getUsername(),userpoll.getEmail(),userpoll.getPassword());

        UserDetailsImpl _io = new UserDetailsImpl(userpoll.getId().toString(),userpoll.getUsername(),userpoll.getEmail());

        return pollService.getPollsVotedBy(username, _io, page, size);
    }
}
