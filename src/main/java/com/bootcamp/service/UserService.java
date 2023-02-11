package com.bootcamp.service;

import com.bootcamp.model.Role;
import com.bootcamp.model.User;
import com.bootcamp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    public String saveUser(String username, String password){

        User user = userRepo.findUserByUsername(username);

        if (user == null){

            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setEnabled(true);
            newUser.setRoles(Collections.singleton(Role.USER));
            userRepo.save(newUser);

            return "User registered";
        }

        return "User already exists, please sign in";
    }

}
