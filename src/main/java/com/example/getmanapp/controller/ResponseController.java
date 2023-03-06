package com.example.getmanapp.controller;


import com.example.getmanapp.model.Response;
import com.example.getmanapp.service.ResponseService;
import com.example.getmanapp.utils.mix.BooleanObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(path = "${v1API}/response")
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    @GetMapping("/{id}")
    public Mono<Response> getResponseById(@PathVariable("id") Long responseId) {
        return responseService.getResponseById(responseId);
    }


    /**
     *
     * заглушка
     */
    @GetMapping("/{id}/await")
    public Mono<BooleanObject> checkResponseAwait(@PathVariable("id") Long responseId,
                                                  @RequestParam(value = "timeout",
                                                          required = false,
                                                          defaultValue = "60") String timeout) {
        return responseService.checkResponseAwait(responseId, timeout);
    }

}
