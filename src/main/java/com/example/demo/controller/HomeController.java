package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/", "/index", "/home"})
    public String index() {
        return "index";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("/header")
    public String header() {
        return "module/header";
    }

    @GetMapping("/footer")
    public String footer() {
        return "module/footer";
    }
}
