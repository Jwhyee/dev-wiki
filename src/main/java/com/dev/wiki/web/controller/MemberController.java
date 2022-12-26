package com.dev.wiki.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login-page";
    }

}
