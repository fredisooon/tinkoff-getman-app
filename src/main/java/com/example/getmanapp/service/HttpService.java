package com.example.getmanapp.service;

import com.example.getmanapp.config.WebClientConfiguration;
import com.example.getmanapp.model.Request;
import com.example.getmanapp.model.Response;
import com.example.getmanapp.model.Status;
import com.example.getmanapp.utils.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Service
public class HttpService {

    @Autowired
    private WebClient defaultWebClient = WebClientConfiguration.webClientFromScratch();
    private static Long executeTime;
    private static Long closeTime;



    public Mono<Response> getInternalRequest(Request request) {
        String fullURI = getFullURI(request);
        HttpMethod requestMethod = HttpMethod.valueOf(request.getMethod());
        executeTime = System.currentTimeMillis();

        if (requestMethod.equals(HttpMethod.GET) || requestMethod.equals(HttpMethod.HEAD)) {

            return defaultWebClient
                    .method(requestMethod)
                    .uri(fullURI)
                    .exchangeToMono(response -> {
                        if (response.statusCode().equals(HttpStatus.OK)) {
                            return response
                                    .bodyToMono(String.class)
                                    .flatMap(body -> getFilledResponse(response, request, body));
                        }
                        else {
                            return response.createError();
                        }
                    });



        } else {
            return Mono.just(new Response(new Status(123, "Error")));
        }
    }

    private Mono<Response> getFilledResponse(ClientResponse clientResponse, Request request, String body) {
        Response resultResponse = new Response();
        resultResponse.setExecuted_at(executeTime);
        resultResponse.setStatus(new Status(clientResponse.statusCode().value(),
                                            clientResponse.statusCode().toString()));

        resultResponse.setHeaders(new HttpHeaders(clientResponse.headers().asHttpHeaders()));
        resultResponse.setPayload(getFilledPayload(clientResponse, body));
        closeTime = System.currentTimeMillis();
        resultResponse.setClosed_a(closeTime);
        return Mono.just(resultResponse);
    }

    private Payload getFilledPayload(ClientResponse clientResponse, String body) {

        Payload payload = new Payload();
        payload.setData(body);
        payload.setType(Objects.requireNonNull(clientResponse.headers().asHttpHeaders().getContentType()).toString());
        return payload;
    }

    public static String getFullURI (Request request){
            StringBuilder fullPath = new StringBuilder(request.getScheme() +
                    "://" +
                    request.getHost() +
                    request.getPath());
            if (!request.getQuery().getQueries().isEmpty()) {
                fullPath.append('?');
                for (List<String> queryPair : request.getQuery().getQueries()) {
                    fullPath.append(queryPair.get(0));
                    fullPath.append('=');
                    fullPath.append(queryPair.get(1));
                    fullPath.append('&');
                }
                fullPath.deleteCharAt(fullPath.length() - 1);
            }
            return fullPath.toString();
        }
    }
