package com.example.getmanapp.webclient;

import com.example.getmanapp.config.WebClientConfiguration;
import com.example.getmanapp.exceptions.exception.SampleDefinedException;
import com.example.getmanapp.model.Request;
import com.example.getmanapp.model.Response;
import com.example.getmanapp.model.Status;
import com.example.getmanapp.utils.Payload;
import com.example.getmanapp.utils.Query;
import com.example.getmanapp.utils.mix.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;

import java.util.Base64;

@Service
@Slf4j
public class ExternalRequester {

    @Autowired
    private WebClient defaultWebClient = WebClientConfiguration.webClientFromScratch();
    private static Long Execute_Time;
    private static Long Close_time;
    private static HttpMethod requestMethod;

    public Mono<Response> getExternalRequest(Request request) {
        if (request == null)
            return Mono.error(new SampleDefinedException("Request object is null"));

        String fullURI = getFullURI(request);
        requestMethod = HttpMethod.valueOf(request.getMethod().toUpperCase());
        Execute_Time = System.currentTimeMillis();

        if (Constants.HTTP_METHODS_WITHOUT_BODY.contains(HttpMethod.valueOf(request.getMethod()))) {
            try {
                return defaultWebClient
                        .method(requestMethod)
                        .uri(fullURI)
                        .headers(headers -> headers.addAll(request.getHeaders()))
                        .exchangeToMono(this::getFilledResponse);
            } catch (WebClientRequestException e) {
                return Mono.error(new SampleDefinedException(e.getMessage()));
            }
        } else if (Constants.HTTP_METHODS_HAVING_BODY.contains(HttpMethod.valueOf(request.getMethod()))) {
            try {
                var payload = request.getPayload();
                return defaultWebClient
                        .method(requestMethod)
                        .uri(fullURI)
                        .headers(headers -> headers.addAll(request.getHeaders()))
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(BodyInserters.fromValue(
                                payload == null ? new String() : payload.getData() == null ? new String() : new String(Base64.getDecoder().decode(payload.getData()))))
                        .exchangeToMono(this::getFilledResponse);
            } catch (WebClientException e) {
                return Mono.error(new SampleDefinedException(e.getMessage()));
            }

        } else {
            return Mono.error(Exception::new);
        }
    }

    private Mono<Response> getFilledResponse(ClientResponse clientResponse) {
        if (clientResponse == null)
            return Mono.error(new SampleDefinedException("Client response is null"));
        log.info("Status of external request: " + clientResponse.statusCode());
        Response resultResponse = new Response();
        resultResponse.setExecuted_at(Execute_Time);
        resultResponse.setStatus(new Status(clientResponse.statusCode().value(),
                clientResponse.statusCode().toString()));

        resultResponse.setHeaders(new HttpHeaders(clientResponse.headers().asHttpHeaders()));
        Close_time = System.currentTimeMillis();
        resultResponse.setClosed_a(Close_time);

        return clientResponse
                .bodyToMono(String.class)
                .flatMap(body -> {
                    if (!requestMethod.equals(HttpMethod.HEAD))
                        resultResponse.setPayload(getFilledPayload(clientResponse, body));
                    return Mono.just(resultResponse);
                });
    }

    private Payload getFilledPayload(ClientResponse clientResponse, String body) {
        Payload payload = new Payload();
        payload.setData(Base64.getEncoder().encodeToString(body.getBytes()));
        // payload.setType(Objects.requireNonNull(clientResponse.headers().asHttpHeaders().getContentType()).toString());
        payload.setType("plain");
        return payload;
    }

    public static String getFullURI(Request request) {
        StringBuilder fullPath = new StringBuilder(request.getScheme().toLowerCase() +
                "://" +
                request.getHost() + ":" +
                request.getPort().toString() +
                request.getPath());
        Query query = request.getQuery();
        if (query != null && !query.getQuery().isEmpty()) {
            fullPath.append('?');
            fullPath.append(String.join("&", query.getQuery()
                    .stream()
                    .map(pair -> String
                            .join("=", pair))
                    .toList()));
        }
        return fullPath.toString();
    }
}
