package com.example.getmanapp.service;

import com.example.getmanapp.exceptions.exception.ResponseNotFoundException;
import com.example.getmanapp.model.Response;
import com.example.getmanapp.repository.ResponseRepository;
import com.example.getmanapp.utils.mix.AdapterLayer;
import com.example.getmanapp.utils.mix.BooleanObject;
import com.example.getmanapp.utils.mix.ResponseResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ResponseService {

    private final ResponseRepository responseRepository;

    public ResponseService(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }

    public Mono<Response> saveResponse(Response response) {
        return responseRepository.save(response);
    }

    public Flux<Response> getAllResponse() {
        return responseRepository.findAll();
    }

    public Mono<ResponseResponse> getResponseById(Long responseId) {
        return responseRepository.findById(responseId)
                .switchIfEmpty(Mono.error(new ResponseNotFoundException(responseId)))
                .flatMap(response -> Mono.just(AdapterLayer.convertToResponseResponse(response)));
    }

    public Mono<Void> deleteResponseById(Long id) {
        return responseRepository.deleteById(id);
    }

    public Mono<BooleanObject> checkResponseAwait(Long id, String timeout) {
        return responseRepository
                .findById(id)
                .timeout(Duration.ofSeconds(Long.parseLong(timeout)))
                .switchIfEmpty(Mono.error(new ResponseNotFoundException(id)))
                .thenReturn(new BooleanObject(Boolean.TRUE))
                .onErrorResume(ex -> Mono.just(new BooleanObject(false, ex.getMessage())));
    }
}
