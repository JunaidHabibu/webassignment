package com.project.project.controllers;

import com.project.project.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/")
    public String homePage()
    {
        return "index.html";
    }

    @RequestMapping("/register")
    public String registerPage()
    {
        return "register.html";
    }

    @RequestMapping("/login")
    public String loginPage()
    {
        return "login.html";
    }

    @GetMapping("/welcome")
    public String welcome(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("firstname", user.getFirstname());
        return "welcome";
    }
}
