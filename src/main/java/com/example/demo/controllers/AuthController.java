package com.example.demo.controllers;

import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtUtil;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authManager;

    @Autowired
    UserService userService;

    public AuthController(AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseCookie> login(@RequestParam String username, @RequestParam String password) {
        var authToken = new UsernamePasswordAuthenticationToken(username, password);
        authManager.authenticate(authToken); // throws exception if invalid
        final String token = JwtUtil.generateToken(username);
        ResponseCookie cookie  = ResponseCookie.from("COOKIE", token).httpOnly(true)
                .path("/").build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(cookie);
    }

    @PostMapping("/register")
    public String createUser(@RequestParam String username, @RequestParam String password) {
        userService.createUser(
                username,
                password
        );
        return "utilisateur créé";
    }
}
