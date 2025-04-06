package com.example.demo.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class HelloService {

    @PreAuthorize("hasRole('USER')")
    public String getHelloUser() throws Exception {
        return "hello user";
    }
    @PreAuthorize("hasRole('ADMIN')")
    public String getHelloAdmin() throws Exception {
        return "hello admin";
    }
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public String getHelloSuperAdmin() throws Exception {
        return "hello super-admin";
    }
}
