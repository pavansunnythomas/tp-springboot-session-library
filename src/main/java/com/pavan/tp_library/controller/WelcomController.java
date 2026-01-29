package com.pavan.tp_library.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WelcomController {
    @GetMapping
    public String welcome() {
        return "Welcome to spring-boot session !";
    }
    
}
