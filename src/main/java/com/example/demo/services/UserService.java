package com.example.demo.services;

import com.example.demo.reposotories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    UserRepository repo;

    public void createUser(String username, String password) {
        List<String> roleList = new ArrayList<>();
        roleList.add("USER");
        repo.save(
                new com.example.demo.models.User(
                        username,
                        bcrypt.encode(password),
                        roleList
                )
        );
    }

    public void createUserAdmin(String username, String password) {
        List<String> roleList = new ArrayList<>();
        roleList.add("ADMIN");
        repo.save(
                new com.example.demo.models.User(
                        username,
                        bcrypt.encode(password),
                        roleList
                )
        );
    }
    public void createUserSuperAdmin(String username, String password) {
        List<String> roleList = new ArrayList<>();
        roleList.add("SUPER_ADMIN");
        repo.save(
                new com.example.demo.models.User(
                        username,
                        bcrypt.encode(password),
                        roleList
                )
        );
    }


}
