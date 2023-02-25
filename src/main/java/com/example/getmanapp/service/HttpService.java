//package com.example.getmanapp.service;
//
//import com.example.getmanapp.model.Request;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.BodyInserters;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//
//@Service
//public class HttpService {
//
//    private final WebClient defaultWebClient;
//
//    public HttpService(WebClient defaultWebClient) {
//        this.defaultWebClient = defaultWebClient;
//    }
//
//    public Mono<String> getInternalRequest(Request request) {
//        String fullURI = getFullURI(request);
//
//        switch (request.getMethod()) {
//            case "GET":
//                System.out.println("Method going through 'test'/GET case method");
//                return  this.defaultWebClient.method(HttpMethod.GET)
//                        .uri(fullURI)
//                        .retrieve()
//                        .bodyToMono(String.class)
//                        .log();
//            case "HEAD":
//                System.out.println("Method going through 'test'/HEAD case method");
//                return this.defaultWebClient.method(HttpMethod.HEAD)
//                        .uri(fullURI)
//                        .retrieve()
//                        .toBodilessEntity()
//                        .map(response -> response.getHeaders().toString());
//
//            case "POST":
//                System.out.println("Method going through 'test'/POST case method");
//                return this.defaultWebClient.method(HttpMethod.POST)
//                        .uri(fullURI)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(BodyInserters.fromValue(request.getPayload().get(1).get(1)))
//                        .retrieve()
//                        .bodyToMono(String.class)
//                        .log();
//            case "PUT":
//                System.out.println("Method going through 'test'/PUT case method");
//                return this.defaultWebClient.method(HttpMethod.PUT)
//                        .uri(fullURI)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(BodyInserters.fromValue(request.getPayload().get(1).get(1)))
//                        .retrieve()
//                        .bodyToMono(String.class)
//                        .log();
//            case "DELETE":
//                System.out.println("Method going through 'test'/DELETE case method");
//                return this.defaultWebClient.method(HttpMethod.DELETE)
//                        .uri(fullURI)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(BodyInserters.fromValue(request.getPayload().get(1).get(1)))
//                        .retrieve()
//                        .bodyToMono(String.class)
//                        .log();
//            default:
//                return null;
//        }
//    }
//
//    public String getFullURI(Request request) {
//        StringBuilder fullPath = new StringBuilder(request.getScheme() +
//                                                    "://" +
//                                                    request.getHost() +
//                                                    request.getPath());
//        if (!request.getQuery().isEmpty()) {
//            fullPath.append('?');
//            for (List<String> queryPair : request.getQuery()) {
//                fullPath.append(queryPair.get(0));
//                fullPath.append('=');
//                fullPath.append(queryPair.get(1));
//                fullPath.append('&');
//            }
//            fullPath.substring(0, fullPath.length()-1);
//        }
//        return fullPath.toString();
//    }
//}
