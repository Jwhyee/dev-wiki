package com.dev.wiki.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String mainPageView() {
        // 주석 추가
        return "Hello, React!";
    }
    @GetMapping("/login")
    public String loginPageView() {
        return "Hi";
    }
}
