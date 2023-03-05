package com.example.getmanapp.service;

import com.example.getmanapp.model.Request;
import com.example.getmanapp.repository.RequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class RequestService{
    private final RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public Mono<Request> saveRequest(Request request, String workspaceId) {
        request.setWorkspace_id(Long.parseLong(workspaceId));
        return requestRepository.save(request);
    }

    public Flux<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public Mono<Request> getRequestById(Long id) {
        return requestRepository.findById(id);
    }

    public Mono<Void> deleteRequestById(Long id) {
        return requestRepository.deleteById(id);
    }
}
