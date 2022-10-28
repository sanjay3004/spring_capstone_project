package com.example.springbootcapstone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @GetMapping("/get")
    public String hello() {
        return "hello_world";
    }



}
