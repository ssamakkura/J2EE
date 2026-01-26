package com.example.baitap.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/")
public class homecontroller {
    @GetMapping("/1")
    public String getMethodName() {
        return "Hello";
    }
    
}
