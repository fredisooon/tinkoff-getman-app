package com.example.getmanapp.controller;


import com.example.getmanapp.utils.mix.BooleanObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(path = "${v1API}/move")
public class MassMoveController {

    @PostMapping()
    public Mono<BooleanObject> massWorkspacesMove() {
        return Mono.just(new BooleanObject(Boolean.TRUE));
    }
}
