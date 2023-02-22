package com.example.getmanapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class GetmanAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetmanAppApplication.class, args);
//        WebClient defaultWebClient = WebClient.builder().build();
//
//        WebClient.ResponseSpec cat = defaultWebClient.get()
//                .uri("https://catfact.ninja/fact?max_length=100")
//                .retrieve();
//        String responseBody = cat.bodyToMono(String.class).block();
//        System.out.println(responseBody);

//        ResponseEntity<String> responseEntity = defaultWebClient.get()
//                .uri("https://catfact.ninja/fact?max_length=100")
//                .retrieve()
//                .toEntity(String.class)
//                .block();
//        System.out.println(responseEntity.getBody());
//        System.out.println("Status code: " + responseEntity.getStatusCode());
//        HttpHeaders headers = responseEntity.getHeaders();
//        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
//            System.out.println(entry);
//        }
    }

}
