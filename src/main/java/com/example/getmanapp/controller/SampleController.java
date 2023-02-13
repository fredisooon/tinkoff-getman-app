package com.example.getmanapp.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class SampleController {

    @GetMapping
    public String getHelloTeam() {
        return "Hello Team :)";
    }
}
