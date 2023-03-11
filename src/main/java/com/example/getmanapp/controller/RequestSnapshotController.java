package com.example.getmanapp.controller;

import com.example.getmanapp.model.RequestSnapshot;
import com.example.getmanapp.service.RequestSnapshotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(path = "${v1API}/request_snapshot")
public class RequestSnapshotController {

    private final RequestSnapshotService requestSnapshotService;

    @Autowired
    public RequestSnapshotController(RequestSnapshotService requestSnapshotService) {
        this.requestSnapshotService = requestSnapshotService;
    }


    @GetMapping("/{id}")
    public Mono<RequestSnapshot> getRequestSnapshot(@PathVariable("id") Long id) {
        return requestSnapshotService.getSnapshotById(id);
    }
}
