package com.ak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";  // This should correspond to a login.html file in src/main/resources/templates
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";  // This should correspond to a register.html file in src/main/resources/templates
    }
}
