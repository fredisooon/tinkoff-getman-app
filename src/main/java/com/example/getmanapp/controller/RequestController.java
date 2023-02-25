package com.example.getmanapp.controller;

import com.example.getmanapp.model.Request;
import com.example.getmanapp.model.Workspace;
import com.example.getmanapp.service.RequestService;
import com.example.getmanapp.utils.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/version/1/request")
public class RequestController {

    private RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
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
    public Mono<Id> createNewRequest(@RequestParam(value = "workspace") String workspaceId,
                                         @RequestBody Request request) {
        if (!workspaceId.equals("0") && !workspaceId.equals("-100")) {
            Mono<Request> newRequest = requestService.saveRequest(request, workspaceId);

            return Mono.just(new Id()).flatMap(e -> newRequest.map(nr -> {
                e.setId(nr.getId());
                e.setParent(Long.parseLong(workspaceId));

                return e;
            }));
        } else {
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
