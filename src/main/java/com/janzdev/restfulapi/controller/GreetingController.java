package com.janzdev.restfulapi.controller;

import com.janzdev.restfulapi.entity.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/greeting")
public class GreetingController {

    @GetMapping("/sayHelloPublic")
    public String sayHello(){
        return "Hello from API Janz";
    }

    @GetMapping("/sayHelloProtected")
    public String sayHelloProtected(){
        return "Hello from API Janz Protected";
    }
}

