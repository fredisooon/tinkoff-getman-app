package com.example.getmanapp.service;

import com.example.getmanapp.exceptions.exception.ResponseNotFoundException;
import com.example.getmanapp.model.Response;
import com.example.getmanapp.repository.ResponseRepository;
import com.example.getmanapp.utils.mix.BooleanObject;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    public Mono<Response> getResponseById(Long responseId) {
        return responseRepository.findById(responseId)
                .switchIfEmpty(Mono.error(new ResponseNotFoundException(responseId)));
    }

    public Mono<Void> deleteResponseById(Long id) {
        return responseRepository.deleteById(id);
    }

    public Mono<BooleanObject> checkResponseAwait(Long id, String timeout) {
        return responseRepository.
                findById(id)
                .thenReturn(new BooleanObject(Boolean.TRUE))
                .defaultIfEmpty(new BooleanObject(Boolean.FALSE));
    }
}
