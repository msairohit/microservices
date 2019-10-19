package com.rohit.ratingsdataservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class TestController {

    @GetMapping("/test")
    public String testMethod() {
        return "ratings data service";
    }
}
