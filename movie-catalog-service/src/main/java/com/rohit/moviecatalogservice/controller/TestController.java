package com.rohit.moviecatalogservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/catalog")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public String testMethod() {
        return "movie catalog service";
    }

    @GetMapping("/movieInfoService")
    public String movieInfoService() {
        return restTemplate.getForObject("http://movie-info-service/info/test", String.class);
    }

    @GetMapping("/ratingsDataService")
    public String ratingsDataService() {
        return restTemplate.getForObject("http://ratings-data-service/ratings/test", String.class);
    }
}