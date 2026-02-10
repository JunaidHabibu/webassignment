package com.project.project.controllers;

import com.project.project.model.User;
import com.project.project.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

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

    @GetMapping("/get")
    public Mono<String> get(@RequestParam String email)
    {
        return userService.getUserFirstName(email)
                .doOnNext(name -> System.out.println("firstname = " + name));
    }
    //i did this last minute while on 4 hours of sleep : im too lazy to attach it to a html page
    //i dont know if it 100% works
}