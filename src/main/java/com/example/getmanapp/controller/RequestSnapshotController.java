package com.example.getmanapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(path = "${v1API}/request_snapshot")
public class RequestSnapshotController {

    @GetMapping
    public Mono<Object> getRequestSnapshot() {
        return null;
    }
}
