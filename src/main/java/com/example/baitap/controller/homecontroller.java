package com.example.baitap.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class homecontroller {
    @GetMapping("/1")
    public String getMethodName() {
        return "Hello, chao ban";
    }
    
    @GetMapping("/2")
    public String Index(){
        return "home";
    }
    @GetMapping("/error")
    public String getMethodName(@RequestParam String param) {
        return new String("Lỗi: " + param);
    }
    
}
    
