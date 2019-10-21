package com.rohit.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RatingsDataService {

    @Qualifier("WebClientBuilder")
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallbackRatingsDataService")
    public String ratingsDataService() {
        return restTemplate.getForObject("http://ratings-data-service/ratings/test", String.class);
    }

    public String fallbackRatingsDataService() {
        return "default message from fallbackRatingsDataService";
    }
}
