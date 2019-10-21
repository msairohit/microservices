package com.rohit.moviecatalogservice.controller;

import com.rohit.moviecatalogservice.service.MovieInfoService;
import com.rohit.moviecatalogservice.service.RatingsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/catalog")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Qualifier("WebClientBuilder")
    @Autowired
    private WebClient.Builder webClientBuilder;

//    @Autowired
//    private WebClient.Builder webClientBuilder;

    @Autowired
    private MovieInfoService movieInfoService;

    @Autowired
    private RatingsDataService ratingsDataService;

    @GetMapping("/test")
    public String testMethod() {
        return "movie catalog service";
    }

    @GetMapping("/combined")
    public String CombinedData() {
        return movieInfoService.movieInfoService() + ratingsDataService.ratingsDataService();
    }

}