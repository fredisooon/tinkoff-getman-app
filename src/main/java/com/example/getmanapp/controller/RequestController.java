package com.example.getmanapp.controller;

import com.example.getmanapp.config.WebClientConfiguration;
import com.example.getmanapp.model.Request;
//import com.example.getmanapp.utils.mix.AdapterLayer;
//import com.example.getmanapp.webclient.ExternalRequester;
import com.example.getmanapp.service.RequestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(path = "${v1API}/request")
public class RequestController {
    @Autowired
    private final WebClient defaultWebClient = WebClientConfiguration.webClientFromScratch();

//    private final ExternalRequester externalRequester;
    private final  RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }


//    public RequestController(ExternalRequester externalRequester, RequestService requestService) {
//        this.externalRequester = externalRequester;
//        this.requestService = requestService1;
//    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<String>> getRequestById(@PathVariable("id") Long id) {
        try {
            return defaultWebClient
                    .method(HttpMethod.GET)
                    .uri("https://catfact.ninja/fact?max_length=100")
                    .retrieve()
                    .toEntity(String.class)
                    .log();
        }
        catch (Exception e) {
            return Mono.error(e);
        }

    }

    @PutMapping("/{id}")
    public String updateRequest(@PathVariable("id") String id,
                                @RequestBody String body) {
        return id + body;
    }

    @PostMapping()
    public Mono<Boolean> createNewRequest(@RequestParam(value = "workspace") Long workspaceId,
                                           @RequestBody Request request) {
        try {
            if (request != null) {
//                request = AdapterLayer.transferRequestModel(requestA);

                request.setWorkspace_id(workspaceId);
                log.info(request.toString());
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(request.getHeaders());
                System.out.println(json); // prints "[[\"foo\",\"bar\"],[\"baz\",\"qux\"]]"
                System.out.println(json.getClass());
                System.out.println(objectMapper.writeValueAsString(request.getPayload()));
                System.out.println(objectMapper.writeValueAsString(request.getQuery()));
//                return Mono.just(Boolean.TRUE);
                return requestService.getSomething(request);
//                return externalRequester.getExternalRequest(request);
            }
            else {
                log.info("In controller catch");
                return Mono.error(Exception::new);
            }
        }
        catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
            return Mono.error(e);
        }
    }


    @DeleteMapping("/{id}")
    public Boolean deleteRequestById(@PathVariable("id") String id) {
        if (Integer.parseInt(id) % 2 == 0)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

    @PostMapping("/{id}/move")
    public Boolean moveRequestToWorkspace(@PathVariable("id") String id,
                                          @RequestBody String body) {
        if ((Integer.parseInt(id) + Integer.parseInt(body)) % 3 == 0)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

}
