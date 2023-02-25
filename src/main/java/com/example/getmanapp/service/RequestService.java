package com.example.getmanapp.service;

import com.example.getmanapp.model.Request;
import com.example.getmanapp.model.Workspace;
import com.example.getmanapp.repository.RequestRepository;
import com.example.getmanapp.utils.Headers;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

@Service
public class RequestService {
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
