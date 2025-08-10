package com.janzdev.restfulapi.controller;

import com.janzdev.restfulapi.entity.Greeting;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/v1/greeting")
public class GreetingController {

    @GetMapping("/sayHelloPublic")
    public String sayHello(){
        return "Hello from API Janz";
    }

    @GetMapping("/sayHelloProtected")
    @PreAuthorize("hasAnyRole('READER', 'MANAGER', 'ADMIN')")
    public String sayHelloProtected(){
        return "Hello from API Janz Protected";
    }
}

