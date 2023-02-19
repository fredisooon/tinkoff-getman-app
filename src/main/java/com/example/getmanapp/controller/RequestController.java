package com.example.getmanapp.controller;

import com.example.getmanapp.controller.requestsClasses.RequestAPI;
import com.example.getmanapp.service.HttpService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/version/1/request")
public class RequestController {

    private final HttpService httpService;

    public RequestController(HttpService httpService) {
        this.httpService = httpService;
    }

    @GetMapping("/{id}")
    public String getRequestById(@PathVariable("id") String id) {
        return "ID : " + id;
    }

    @PutMapping("/{id}")
    public String updateRequest(@PathVariable("id") String id,
                                @RequestBody String body) {
        return id + body;
    }

    @PostMapping()
    public Mono<String> createNewRequest(@RequestParam(value = "workspace", required = false) String workspaceId,
                                         @RequestBody RequestAPI requestAPI) {
        try {
            return httpService.getInternalRequest(requestAPI);
        }
        catch (Exception e) {
            e.printStackTrace();
            return Mono.empty();
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
