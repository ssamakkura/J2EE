package com.example.baitap.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
public class homecontroller {
    @GetMapping("/1")
    public String getMethodName() {
        return "Hello";
    }
    
    @GetMapping("/home")
    public String Index(){
        return "redirect:/index.html";
    }
}
    
