package com.example.getmanapp.controller;

import com.example.getmanapp.config.WebClientConfiguration;
import com.example.getmanapp.model.Request;
import com.example.getmanapp.model.Response;
import com.example.getmanapp.webclient.ExternalRequester;
import com.example.getmanapp.service.RequestService;
import com.example.getmanapp.utils.mix.AdapterLayer;
import com.example.getmanapp.utils.mix.RequestAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.example.getmanapp.model.Workspace;
import com.example.getmanapp.utils.Id;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/version/1/request")
public class RequestController {
    @Autowired
    private final WebClient defaultWebClient = WebClientConfiguration.webClientFromScratch();

    private final ExternalRequester externalRequester;
    private final  RequestService requestService;


    public RequestController(ExternalRequester externalRequester, RequestService requestService) {
        this.externalRequester = externalRequester;
    }

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
    public Mono<Response> createNewRequest(@RequestParam(value = "workspace") String workspaceId,
                                           @RequestBody RequestAdapter requestAdapter) {
        try {
            if (requestAdapter != null) {
                Request request = AdapterLayer.transferRequestModel(requestAdapter);
                request.setWorkspace_id(Long.parseLong(workspaceId));
                return externalRequester.getExternalRequest(request);
            }
            else
                return Mono.error(Exception::new);
        }
        catch (Exception e) {
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
