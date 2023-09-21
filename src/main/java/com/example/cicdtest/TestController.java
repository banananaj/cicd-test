package com.example.cicdtest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/")
    public String test(){
        return "test";
    }
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
