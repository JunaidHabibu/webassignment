package com.project.project.controllers;

import com.project.project.model.User;
import com.project.project.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @PostMapping("/register")
    public User Register(@RequestBody User user, HttpSession session)
    {
        User savedUser = userService.registerUser(user);
        session.setAttribute("user", savedUser);
        System.out.println(user.toString());
        return savedUser;
    }

    @PostMapping("/login")
    public boolean login(@RequestBody User user, HttpSession session) {

        boolean exists = userService.getUser(user);

        if (exists) {
            session.setAttribute("userEmail", user.getEmail());
            session.setAttribute("fName", user.getFirstname());
        }

        return exists;
    }
}