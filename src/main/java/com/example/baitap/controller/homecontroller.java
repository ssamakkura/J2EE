package com.example.baitap.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/1")
    @ResponseBody
    public String getMethodName() {
        return "Hello, chao ban";
    }
    
    @GetMapping("/2")
    public String Index(){
        return "home";
    }
    @GetMapping("/error")
    @ResponseBody
    public String getMethodName(@RequestParam String param) {
        return new String("Lỗi: " + param);
    }
    
}
    
