package com.example.demo.controllers;


import com.example.demo.models.User;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {


    @GetMapping("/get-public")
    public String getHelloPublic() throws Exception {

        return "hello you";
    }

    @GetMapping("/get-private")
    public String getHelloPrivate() throws Exception {

        return "hello darling";
    }

    @GetMapping("/say-hello/{name}")
    public String getHelloPrivate(@PathVariable String name) throws Exception {

        return "hello " + name;
    }
}
