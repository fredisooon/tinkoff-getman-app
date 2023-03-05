package com.example.getmanapp.service;

import com.example.getmanapp.exceptions.exception.WorkspaceSavingException;
import com.example.getmanapp.model.Request;
import com.example.getmanapp.model.TemoRequest;
import com.example.getmanapp.repository.RequestRepository;
import com.example.getmanapp.utils.Id;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@Service
public class RequestService{
    private final RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public Mono<Boolean> getSomething(Request request) {
        try {
            log.info("Before saving in repo");
            return requestRepository.save(request)
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(e -> Mono.just(true))
                    .log();

//            workspaceRepository.save(workspace)
//                    .switchIfEmpty(Mono.error(new WorkspaceSavingException(workspace)))
//                    .flatMap(e -> Mono.just(new Id(e.getId(), e.getWorkspace_fk_id())))
//                    .log();
        }
        catch (Exception ex) {
            log.info(ex.getMessage());
            log.info("In throwing exception");
            return Mono.error(new Exception(ex.getMessage()));
        }
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
