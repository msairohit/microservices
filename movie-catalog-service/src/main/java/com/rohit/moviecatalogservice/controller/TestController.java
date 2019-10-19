package com.rohit.moviecatalogservice.controller;

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

    @GetMapping("/test")
    public String testMethod() {
        return "movie catalog service";
    }

    @GetMapping("/movieInfoService")
    public String movieInfoService() {

        String webResult = webClientBuilder.build()
                .get()
                .uri("http://localhost:8082/info/test")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        String restTemplateResult = restTemplate.getForObject("http://movie-info-service/info/test", String.class);
        return webResult;
    }

    @GetMapping("/ratingsDataService")
    public String ratingsDataService() {
        return restTemplate.getForObject("http://ratings-data-service/ratings/test", String.class);
    }
}