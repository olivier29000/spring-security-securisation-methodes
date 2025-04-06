package com.example.demo.controllers;


import com.example.demo.models.User;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.services.HelloService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/public")
    public String getHelloPublic() throws Exception {
        return "hello public";
    }

    @GetMapping("/user")
    public String getHelloUser() throws Exception {
        return helloService.getHelloUser();
    }

    @GetMapping("/admin")
    public String getHelloAdmin() throws Exception {
        return helloService.getHelloAdmin();
    }

    @GetMapping("/super-admin")
    public String getHelloSuperAdmin() throws Exception {
        return helloService.getHelloSuperAdmin();
    }
}
