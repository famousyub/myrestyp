package com.example.crudmn.service;


import com.example.crudmn.models.User;
import com.example.crudmn.models.psn.DoubledObjectEntity;
import com.example.crudmn.models.psn.IdObjectEntity;
import com.example.crudmn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserPsnService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptEncoder;

    public ResponseObjectService findAll() {
        ResponseObjectService responseObj = new ResponseObjectService();
        responseObj.setPayload(userRepo.findAll());
        responseObj.setStatus("success");
        responseObj.setMessage("success");
        return responseObj;
    }

    public ResponseObjectService findById(String id) {
        ResponseObjectService responseObj = new ResponseObjectService();
        Optional<User> optUser = userRepo.findById(id);
        if (optUser.isEmpty()) {
            responseObj.setStatus("fail");
            responseObj.setMessage("user id: " + id + " not existed");
            responseObj.setPayload(null);
            return responseObj;
        } else {
            responseObj.setPayload(optUser.get());
            responseObj.setStatus("success");
            responseObj.setMessage("success");
            return responseObj;
        }
    }

    public ResponseObjectService findFollowing(String id) {
        ResponseObjectService responseObj = new ResponseObjectService();
        Optional<User> optUser = userRepo.findById(id);
        if (optUser.isEmpty()) {
            responseObj.setStatus("fail");
            responseObj.setMessage("user id: " + id + " not existed");
            responseObj.setPayload(null);
            return responseObj;
        } else {
            List<String> followingIds = optUser.get().getFollowing();
            List<User> followingAccounts = new ArrayList<>();
            if (followingIds.size() > 0) {
                for (String followingId : followingIds) {
                    Optional<User> optFollowingUser = userRepo.findById(followingId);
                    if (optFollowingUser.isPresent()) {
                        User followingUser = optFollowingUser.get();
                        followingUser.setPassword("");
                        followingAccounts.add(followingUser);
                    }
                }
                responseObj.setStatus("success");
                responseObj.setMessage("success");
                responseObj.setPayload(followingAccounts);
                return responseObj;
            } else {
                responseObj.setStatus("fail");
                responseObj.setMessage("User id " + id + " does not follow anyone");
                responseObj.setPayload(null);
                return responseObj;
            }
        }
    }

    public ResponseObjectService findFollower(String id) {
        ResponseObjectService responseObj = new ResponseObjectService();
        Optional<User> optUser = userRepo.findById(id);
        if (optUser.isEmpty()) {
            responseObj.setStatus("fail");
            responseObj.setMessage("user id: " + id + " not existed");
            responseObj.setPayload(null);
            return responseObj;
        } else {
            List<String> followerIds = optUser.get().getFollower();
            List<User> followerAccounts = new ArrayList<>();
            if (followerIds.size() > 0) {
                for (String followerId : followerIds) {
                    Optional<User> optFollowerUser = userRepo.findById(followerId);
                    if (optFollowerUser.isPresent()) {
                        User followerUser = optFollowerUser.get();
                        followerUser.setPassword("");
                        followerAccounts.add(followerUser);
                    }
                }
                responseObj.setStatus("success");
                responseObj.setMessage("success");
                responseObj.setPayload(followerAccounts);
                return responseObj;
            } else {
                responseObj.setStatus("fail");
                responseObj.setMessage("User id " + id + " does not have any follower");
                responseObj.setPayload(null);
                return responseObj;
            }
        }
    }

    public ResponseObjectService followUser(DoubledObjectEntity doubleId) {
        // id1 - followed user, id2 - follower

        ResponseObjectService responseObj = new ResponseObjectService();
        Optional<User> optFollowedUser = userRepo.findById(doubleId.getId1());
        Optional<User> optFollower = userRepo.findById(doubleId.getId2());
        if (optFollowedUser.isEmpty() || optFollower.isEmpty()) {
            responseObj.setStatus("fail");
            responseObj.setMessage("invalid user id");
            responseObj.setPayload(null);
            return responseObj;
        } else {
            User followedUser = optFollowedUser.get();
            User follower = optFollower.get();

            // add to follower list
            List<String> followerList = followedUser.getFollower();
            if (followerList == null) {
                followerList = new ArrayList<>();
            }
            followerList.add(follower.getId());
            followedUser.setFollower(followerList);

            // add to following list
            List<String> followingList = follower.getFollowing();
            if (followingList == null) {
                followingList = new ArrayList<>();
            }
            followingList.add(followedUser.getId());
            follower.setFollowing(followingList);

            userRepo.save(followedUser);
            userRepo.save(follower);

            responseObj.setStatus("success");
            responseObj.setMessage(
                    "User id " + follower.getId() + " successfully followed user id " + followedUser.getId());
            responseObj.setPayload(new IdObjectEntity(doubleId.getId1()));
            return responseObj;
        }
    }

    public boolean updateWithoutPassword(User inputUser) {
        Optional<User> optUser = userRepo.findById(inputUser.getId());
        if (optUser.isEmpty()) {
            return false;
        } else {
            User currentUser = optUser.get();
            if (inputUser.getPassword().equals(currentUser.getPassword())) {
                userRepo.save(inputUser);
                return true;
            } else {
                return false;
            }
        }
    }

    public ResponseObjectService update(User inputUser) {
        ResponseObjectService responseObj = new ResponseObjectService();
        Optional<User> optUser = userRepo.findById(inputUser.getId());
        if (optUser.isEmpty()) {
            responseObj.setStatus("fail");
            responseObj.setMessage("user id: " + inputUser.getId() + " not existed");
            responseObj.setPayload(null);
            return responseObj;
        } else {
            User currentUser = optUser.get();
            if (bCryptEncoder.matches(inputUser.getPassword(), currentUser.getPassword())) {
                inputUser.setPassword(bCryptEncoder.encode(inputUser.getPassword()));
                responseObj.setPayload(userRepo.save(inputUser));
                responseObj.setStatus("success");
                responseObj.setMessage("success");
                return responseObj;
            } else {
                responseObj.setStatus("fail");
                responseObj.setMessage("current password is not correct");
                responseObj.setPayload(null);
                return responseObj;
            }
        }
    }

    public ResponseObjectService unfollowUser(DoubledObjectEntity doubleId) {
        // id1 - followed user, id2 - follower

        ResponseObjectService responseObj = new ResponseObjectService();
        Optional<User> optFollowedUser = userRepo.findById(doubleId.getId1());
        Optional<User> optFollower = userRepo.findById(doubleId.getId2());
        if (optFollowedUser.isEmpty() || optFollower.isEmpty()) {
            responseObj.setStatus("fail");
            responseObj.setMessage("invalid user id");
            responseObj.setPayload(null);
            return responseObj;
        } else {
            User followedUser = optFollowedUser.get();
            User follower = optFollower.get();

            // add to follower list
            List<String> followerList = followedUser.getFollower();
            if (followerList == null) {
                followerList = new ArrayList<>();
            }
            followerList.remove(follower.getId());
            followedUser.setFollower(followerList);

            // add to following list
            List<String> followingList = follower.getFollowing();
            if (followingList == null) {
                followingList = new ArrayList<>();
            }
            followingList.remove(followedUser.getId());
            follower.setFollowing(followingList);

            userRepo.save(followedUser);
            userRepo.save(follower);

            responseObj.setStatus("success");
            responseObj.setMessage(
                    "User id " + follower.getId() + " successfully unfollowed user id " + followedUser.getId());
            responseObj.setPayload(new IdObjectEntity(doubleId.getId1()));
            return responseObj;
        }
    }



}
