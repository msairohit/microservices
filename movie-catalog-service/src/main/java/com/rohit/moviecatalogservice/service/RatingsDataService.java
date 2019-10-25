package com.rohit.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

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

    public String circuitBreakerResilience4J() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(5)
                .waitDurationInOpenState(Duration.ofSeconds(5))
                .build();

        return "";
    }
}
