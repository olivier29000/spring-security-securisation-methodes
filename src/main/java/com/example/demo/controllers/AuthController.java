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
import org.springframework.security.core.GrantedAuthority;
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


    @Autowired
    CustomUserDetailsService  customUserDetailsService;
    public AuthController(AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        var authToken = new UsernamePasswordAuthenticationToken(username, password);
        authManager.authenticate(authToken);

        var userDetails = customUserDetailsService.loadUserByUsername(username);
        var roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String jwt = JwtUtil.generateToken(username, roles);
        ResponseCookie cookie = ResponseCookie.from("jwt", jwt)
                .httpOnly(true)
                .path("/")
                .maxAge(86400)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Connecté avec succès");
    }

    @PostMapping("/register")
    public String createUser(@RequestParam String username, @RequestParam String password) {
        userService.createUser(
                username,
                password
        );
        return "user créé";
    }

    @PostMapping("/register-admin")
    public String createUserAdmin(@RequestParam String username, @RequestParam String password) {
        userService.createUserAdmin(
                username,
                password
        );
        return "admin créé";
    }

    @PostMapping("/register-super-admin")
    public String createUserSuperAdmin(@RequestParam String username, @RequestParam String password) {
        userService.createUserSuperAdmin(
                username,
                password
        );
        return "super-admin créé";
    }
}
