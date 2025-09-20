package com.example.demo.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AutoController {

    @GetMapping("/auto")
    public String auto() {
        return "auto";
    }
    
}
