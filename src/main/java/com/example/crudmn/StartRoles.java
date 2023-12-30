package com.example.crudmn;

import com.example.crudmn.entity.RoleName;
import com.example.crudmn.entity.Roles;
import com.example.crudmn.entity.Userpoll;
import com.example.crudmn.models.ERole;
import com.example.crudmn.models.Role;
import com.example.crudmn.repository.RolePollRepository;
import com.example.crudmn.repository.RoleRepository;
import com.example.crudmn.repository.UserPollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

//@Component
public class StartRoles implements CommandLineRunner {

  // @Autowired
    //private RoleRepository roleRepo ;
   //@Autowired
   //private PasswordEncoder passwordEncoder;


 //  @Autowired
   //private UserPollRepository userPollRepository;
  @Autowired
    private RolePollRepository roleRepo ;


    @Override
    public void run(String... args) throws Exception {

        Roles role =new Roles();

        role.setName(RoleName.ROLE_USER);

       // Roles role1 =new Roles();

       // role1.setName(RoleName.ROLE_TEACHER);
     // roleRepo.save(role1);
      roleRepo.save(role);

/*
        Role r = new Role();
        Role r1 = new Role();
   r.setName(ERole.ROLE_USER);
   r1.setName(ERole.ROLE_ADMIN);
        //Roles role2 =new Roles();

        //role2.setName(RoleName.ROLE_TEACHER);
        roleRepo.save(r);
  //roleRepo.save(role1);
        roleRepo.save(r1);


        Set<Roles> roles = new HashSet<>();
        roles.add(role1);
        roles.add(role);
        Userpoll userpoll =new Userpoll();
        userpoll.setAge(25);
        userpoll.setUsername("ayoubo");
        userpoll.setFirstname("ayoubo");
        userpoll.setLastname("ayoubo");
        userpoll.setPhonenumber("29966019");
        userpoll.setName("ayubo");
        userpoll.setActive(true);
        userpoll.setActivationCode("147852369az");
        userpoll.setPasswordResetCode("er_ui");
        userpoll.setEmail("thejobs@gmail.com");
        userpoll.setPassword(passwordEncoder.encode("147852369az"));
        userpoll.setCity("nabeul");
        userpoll.setAddress("rue ismailya");
        userpoll.setRoles(roles);

        userPollRepository.save(userpoll);
*/
//https://github.com/ujjavaldesai07/spring-boot-react-ecommerce-app
    }
}
