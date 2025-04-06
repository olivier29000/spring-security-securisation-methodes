package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageLoginController {
    @GetMapping("/log-user")
    public String login() {
        return "log-user-page";
    }

    @GetMapping("/register")
    public String createUserPage() {
        return "create-user";
    }

    @GetMapping("/register-admin")
    public String createUserAdminPage() {
        return "create-user-admin";
    }

}
