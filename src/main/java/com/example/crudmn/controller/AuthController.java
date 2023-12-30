package com.example.crudmn.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.example.crudmn.entity.RoleName;
import com.example.crudmn.entity.Roles;
import com.example.crudmn.entity.UserDetail;
import com.example.crudmn.entity.Userpoll;
import com.example.crudmn.exception.AppException;
import com.example.crudmn.helpers.UserCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crudmn.payload.request.LoginRequest;
import com.example.crudmn.payload.request.SignupRequest;
import com.example.crudmn.payload.response.JwtResponse;
import com.example.crudmn.payload.response.MessageResponse;


import com.example.crudmn.repository.*;
import com.example.crudmn.security.services.UserDetailsImpl;
import com.example.crudmn.models.*;
import com.example.crudmn.security.jwt.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;


	@Autowired
	UserPollRepository userPollRepository;
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	RolePollRepository rolePollRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	UserDetailRepository userDetailRepository;
	@Autowired
	JwtUtils jwtUtils;

	@Value("${redis_server_app.url}")
	private String url;
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) {

		 //final  String URL_ ="http://localhost:8090/api/v0/add/";
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> map = new HashMap<>();

		map.put("name", loginRequest.getUsername());
		map.put("userame", loginRequest.getUsername());
		map.put("token",userDetails.getPassword());


		List<String> notes = (List<String>) request.getSession().getAttribute("NOTES_SESSION");
		//check if notes is present in session or not
		if (notes == null) {
			notes = new ArrayList<>();
			notes.add(map.get("name"));
			notes.add(map.get("username"));
			notes.add(map.get("token"));
			// if notes object is not present in session, set notes in the request session
			request.getSession().setAttribute("NOTES_SESSION", notes);



		}
		notes.add(loginRequest.getUsername());
		request.getSession().setAttribute("NOTES_SESSION", notes);

		UserCache userCache = new UserCache();

		userCache.setId(Long.parseLong("1"));
		userCache.setName(loginRequest.getUsername());
		userCache.setUsername(loginRequest.getUsername());
		userCache.setToken(loginRequest.getPassword());



		//ResponseEntity<UserCache> response = restTemplate.postForEntity(url, userCache, UserCache.class);

// check response
	/*	if (response.getStatusCode() == HttpStatus.OK) {
			System.out.println("Request Successful");
		} else {
			System.out.println("Request Failed");
		}
*/
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}


		//System.out.println(signUpRequest.getPhonenumber().toString());

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword())

		);

		Userpoll userpoll = new Userpoll(signUpRequest.getUsername(), signUpRequest.getUsername(),
				signUpRequest.getEmail(), signUpRequest.getPassword());


		  userpoll.setCity("here");
		  userpoll.setActive(true);
		  userpoll.setPasswordResetCode("123456789ui");
		  userpoll.setActivationCode(new Date().toString() +"  "  + signUpRequest.getEmail());


		  userpoll.setAge(signUpRequest.getAge());
		userpoll.setPassword(encoder.encode(user.getPassword()));

		/*Role userRole_ = roleRepository.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new AppException("User Role not set."));

		userpoll.setRoles(Collections.singleton(userRole_));*/

		userpoll.setFirstname(signUpRequest.getEmail());
		userpoll.setLastname(signUpRequest.getUsername());
        userpoll.setName(signUpRequest.getUsername());
		if(!signUpRequest.getPhonenumber().equals("")){user.setPhonenumber(signUpRequest.getPhonenumber()); userpoll.setPhonenumber(signUpRequest.getPhonenumber());}




		else  {
			 user.setPhonenumber("00000000");
			 userpoll.setPhonenumber("00000000");
		}
		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();


		Roles userRole_ = rolePollRepository.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new AppException("User Role not set."));


		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));

					roles.add(userRole);
				}
			});
		}

		UserDetail user_ = new UserDetail(signUpRequest.getUsername(),encoder.encode(signUpRequest.getPassword()),signUpRequest.getUsername() , signUpRequest.getUsername()+"me", signUpRequest.getEmail());


		userpoll.setRoles(Collections.singleton(userRole_));
		user_.setLastUpdated(LocalDate.now());

		System.out.println("userpoll added");
		user.setRoles(roles);
		Userpoll result = userPollRepository.save(userpoll);
		System.out.println("user " + userpoll.getEmail());
		userRepository.save(user);


		System.out.println("userdetails added");
		userDetailRepository.save(user_);

	/*	URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();*/

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}