package com.example.getmanapp.service;

import com.example.getmanapp.exceptions.exception.RequestSnapshotNotFoundException;
import com.example.getmanapp.model.RequestSnapshot;
import com.example.getmanapp.repository.RequestSnapshotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class RequestSnapshotService {
    private final RequestSnapshotRepository requestSnapshotRepository;

    @Autowired
    public RequestSnapshotService(RequestSnapshotRepository requestSnapshotRepository) {
        this.requestSnapshotRepository = requestSnapshotRepository;
    }


    public Mono<RequestSnapshot> getSnapshotById(Long id) {
        return requestSnapshotRepository.findById(id)
                .switchIfEmpty(Mono.error(new RequestSnapshotNotFoundException(id)));
    }
}
