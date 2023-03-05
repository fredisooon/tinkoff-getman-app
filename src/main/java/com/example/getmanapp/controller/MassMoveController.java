package com.example.getmanapp.controller;


import com.example.getmanapp.service.MassMoveService;
import com.example.getmanapp.utils.mix.BooleanObject;
import com.example.getmanapp.utils.mix.MassMoveItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "${v1API}/move")
public class MassMoveController {

    @Autowired
    private MassMoveService massMoveService;

    @PostMapping()
    public Mono<BooleanObject> massWorkspacesMove(@RequestBody List<MassMoveItem> moveList) {
        return massMoveService.massMove(moveList);
    }
}
