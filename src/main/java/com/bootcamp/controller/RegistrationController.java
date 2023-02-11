package com.bootcamp.controller;


import com.bootcamp.repo.UserRepo;
import com.bootcamp.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@SecurityRequirement(name = "auction")
public class RegistrationController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;
    @PostMapping("registration")
    public String saveUser(@RequestParam String username, @RequestParam String password){

        return userService.saveUser(username, password);

    }

    @GetMapping("check")
    public String check(){

        return "Works";
    }
}
