package com.example.crudmn.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v4")
public class CookieController {

    @GetMapping("/")
    public ResponseEntity<?> readCookie(@CookieValue(value = "username", defaultValue = "me") String username) {

        return ResponseEntity.ok().body("Hey! My username is " + username);
    }

    @GetMapping("/change-username")
    public String setCookie(HttpServletResponse response, Authentication me) {

        UserDetails user = (UserDetails) me.getPrincipal();
        // create a cookie
        Cookie cookie = new Cookie("username", user.getUsername());
        cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/"); // global cookie accessible every where

        //add cookie to response
        response.addCookie(cookie);

        return "Username is changed!";
    }

    @GetMapping("/delete-username")
    public String deleteCookie(HttpServletResponse response) {

        // create a cookie
        Cookie cookie = new Cookie("username", null);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        //add cookie to response
        response.addCookie(cookie);

        return "Username is deleted!";
    }

    @GetMapping("/all-cookies")
    public String readAllCookies(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return Arrays.stream(cookies)
                    .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
        }

        return "No cookies";
    }

    @GetMapping("/home")
    public ResponseEntity<String> home(Model model, HttpSession session) {
        List<String> notes = (List<String>) session.getAttribute("NOTES_SESSION");
        model.addAttribute("notesSession", notes!=null? notes:new ArrayList<>());
        return ResponseEntity.ok().body("home");
    }
    @PostMapping("/invalidate/session")
    public ResponseEntity<?> destroySession(HttpServletRequest request) {
        //invalidate the session , this will clear the data from configured database (Mysql/redis/hazelcast)
        request.getSession().invalidate();
        return ResponseEntity.ok().body("home");
    }
}
