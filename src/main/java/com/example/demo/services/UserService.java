package com.example.demo.services;

import com.example.demo.reposotories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    UserRepository repo;

    public void createUser(String username, String password) {
        repo.save(
                new com.example.demo.models.User(
                        username,
                        bcrypt.encode(password)
                )
        );
    }


}
