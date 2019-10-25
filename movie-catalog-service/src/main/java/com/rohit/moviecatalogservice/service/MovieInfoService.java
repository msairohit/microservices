package com.rohit.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MovieInfoService {

    @Qualifier("WebClientBuilder")
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallbackMovieInfoService")
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

    public String fallbackMovieInfoService() {
        return "default method from fallbackMovieInfoService";
    }

    @HystrixCommand(
            fallbackMethod = "fallbackMovieInfoService",
            threadPoolKey = "movieInfoPoolKey",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "20"),
                    @HystrixProperty(name = "maxQueueSize", value = "5")
            }
    )

    public String bulkHeadPattern() {
        String webResult = webClientBuilder.build()
                .get()
                .uri("http://localhost:8082/info/test")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        String restTemplateResult = restTemplate.getForObject("http://movie-info-service/info/test", String.class);
        return webResult;
    }
}
