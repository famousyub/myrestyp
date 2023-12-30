package com.example.crudmn.controller;

import com.example.crudmn.entity.UserDetail;
import com.example.crudmn.entity.Userpoll;
import com.example.crudmn.helpers.UserInfo;
import com.example.crudmn.models.Account;
import com.example.crudmn.models.User;
import com.example.crudmn.payload.request.AccountRequest;
import com.example.crudmn.payload.request.PhotoRequest;
import com.example.crudmn.payload.request.SimpleUserInfo;
import com.example.crudmn.payload.request.UserInfoRequest;
import com.example.crudmn.payload.response.PhotosResponse;
import com.example.crudmn.payload.response.UserInfoResponse;
import com.example.crudmn.payload.response.UserSummary;
import com.example.crudmn.repository.AcoountUserRepository;
import com.example.crudmn.repository.UserDetailRepository;
import com.example.crudmn.repository.UserPollRepository;
import com.example.crudmn.repository.UserRepository;
import com.example.crudmn.security.services.CurrentUser;
import com.example.crudmn.security.services.UserPrincipal;
import com.example.crudmn.service.UserInfoService;
import com.example.crudmn.service.UserPhoto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v2")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserSettingController {


    @Autowired
    UserRepository  userRepository;

    @Autowired
    UserPollRepository userRepo;


    @Autowired
    UserPhoto userPhoto;

    @Autowired
    AcoountUserRepository accRepo;

    @Autowired
    UserInfoService userinfo ;

    private byte[] myfile;

    private byte[] avatar ;

    @Autowired
    UserDetailRepository userDetailRepository;


    @PostMapping("/avatar")
    public ResponseEntity<?>  uploadAvater(@RequestParam("avater") MultipartFile file) throws IOException
    {


        if(file.getBytes().length < 0){
            throw  new IOException("error  file not upload");
            // return ResponseEntity.badRequest().body("error soemthing wrong");
        }

        this.avatar = file.getBytes();



        return ResponseEntity.ok().body("uploaded sucesfully");
    }


    @GetMapping("/ipuser")
   // public  ResponseEntity<?>  getCureentUser(@CurrentUser UserPrincipal user_)
    public  ResponseEntity<?>  getCureentUser( Authentication user_)
    {
        UserDetails me_ = (UserDetails)  user_.getPrincipal();

        User me = userRepository.findByUsername(me_.getUsername()).get();
        UserSummary userSummary =new UserSummary(Long.parseLong("1") , me.getUsername(),me.getEmail());

        return ResponseEntity.ok().body(userSummary);




    }

    @PostMapping("/craeteinfo")
    public ResponseEntity<?> craeteAvatar(Authentication auth ,@RequestBody SimpleUserInfo userinforeq)
    {
        UserInfoRequest uer_ = new UserInfoRequest();


        uer_.setFirstname(userinforeq.getFirstname());
        uer_.setLastname(userinforeq.getLastname());
        uer_.setContact(userinforeq.getContact());

         uer_.setAvater(this.avatar);

          UserInfoResponse inf =  userinfo.createUserInfo(auth,uer_);

          return ResponseEntity.ok().body(inf);

    }

    @PostMapping("/upload")
    public ResponseEntity<?>  uploadAndServe(@RequestParam("file") MultipartFile file) throws IOException
    {


           if(file.getBytes().length < 0){
                throw  new IOException("error  file not upload");
               // return ResponseEntity.badRequest().body("error soemthing wrong");
           }

           this.myfile = file.getBytes();



        return ResponseEntity.ok().body("uploaded sucesfully");
    }





    @PostMapping("/addphoto")
    public ResponseEntity<?>  addphototome(Authentication auth)
    {
        if(this.myfile.length >0 && this.myfile.equals(null) != true){
            PhotoRequest phreq = new PhotoRequest();
            phreq.setPhoto(this.myfile);
            PhotosResponse  phr= userPhoto.addPhoto(auth,phreq);
            return ResponseEntity.ok().body(phr);

        }
        return ResponseEntity.badRequest().body("something wrong");



    }

    @GetMapping("/me")
    public ResponseEntity<?>  theMe(Authentication auth)
    {

        UserDetails user_ = (UserDetails)  auth.getPrincipal();


        return ResponseEntity.ok().body(user_.getUsername());

    }



    @PostMapping("/myaccount")
   public  ResponseEntity<?> updateUserAccount(Authentication auth,@Valid AccountRequest accRequest)
    {


        UserDetails _user = (UserDetails)  auth.getPrincipal();
        List<Account> _i = new ArrayList<>();
        Account acc = new Account();
        acc.setAccName(_user.getUsername() + " " +  new Date().toString());
        acc.setAccNo(accRequest.getAccNo());
        acc.setAmount(accRequest.getAmount());
        acc.setCurrency(new Date().toString());
        accRepo.save(acc);



        User _theme = userRepository.findByUsername(_user.getUsername()).get();

       Account _one =  accRepo.findByAccName(acc.getAccName()).get();
        _i.add(_one);
        _theme.setAccounts(_i);

        userRepository.save(_theme);

        return ResponseEntity.ok().body("profile update succesfully");





    }



    @PostMapping(value = "/findv0")
    public ResponseEntity<String> findUser(@RequestBody UserInfo ums) {
//		HashMap<String, List<UserDetails>> userresponse = new HashMap<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        UserDetail ud = new UserDetail();

        List<UserDetail> users = new ArrayList<>();
        for(String s : ums.getUsername()) {
            Userpoll user = userRepo.findByUsername(s).get();
            if(user != null) {
                ud.setId(user.getId());
                ud.setFirstname(user.getUsername());
                ud.setLastname(user.getUsername());
                ud.setEmailID(user.getEmail());
               // ud.setLastUpdated(LocalDate.now());
                users.add(ud);
            }
        }

        String json = gson.toJson(users);
//		userresponse.put("username",users);
        return ResponseEntity.ok(json);
    }


    @PostMapping(value = "/find")
    public ResponseEntity<String> findUser( @RequestParam("username") String  username) {

/*        List<String> userAllname = new ArrayList<>();
        List<String> u = userRepo.findAllUsername();


        for (String name:u
             ) {
            System.out.println(name);
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(u);
	HashMap<String, List<UserDetails>> userresponse = new HashMap<>();*/
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        UserDetail ud = new UserDetail();

        Userpoll user = userRepo.findByUsername(username).get();

        if(user!=null){
            ud.setId(user.getId());
            ud.setFirstname(user.getUsername());
            ud.setLastname(user.getUsername());
            ud.setEmailID(user.getEmail());
            ud.setLastUpdated(LocalDate.now());

            String json = gson.toJson(ud);
            //userDetailRepository.saveAll(users);
//		userresponse.put("username",users);
            return ResponseEntity.ok(json);
        }

        else  {
            return  ResponseEntity.badRequest().body("something wrong refresh");
        }

        /*List<UserDetail> users = new ArrayList<>();
        Integer counter = 0;
        for(String s : userInfo.getUsername()) {
            Userpoll user = userRepo.findByUsername(s).get();
            System.out.println(user.getEmail());
            if(user != null) {
                counter++;
                ud.setId(user.getId());
                ud.setFirstname(user.getUsername());
                ud.setLastname(user.getUsername());
                ud.setEmailID(user.getEmail());
                ud.setLastUpdated(LocalDate.now());
                users.add(ud);
            }
        }*/


        //String json = gson.toJson(users);
        //userDetailRepository.saveAll(users);
//		userresponse.put("username",users);
        //return ResponseEntity.ok(json);
    }



}
