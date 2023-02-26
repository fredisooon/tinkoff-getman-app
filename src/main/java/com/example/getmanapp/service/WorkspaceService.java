package com.example.getmanapp.service;

import com.example.getmanapp.model.Request;
import com.example.getmanapp.model.Workspace;
import com.example.getmanapp.repository.RequestRepository;
import com.example.getmanapp.repository.WorkspaceRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final RequestRepository requestRepository;

    public WorkspaceService(WorkspaceRepository workspaceRepository, RequestRepository requestRepository) {
        this.workspaceRepository = workspaceRepository;
        this.requestRepository = requestRepository;
    }

    public Mono<Workspace> saveWorkspace(Workspace workspace) {
        return workspaceRepository.save(workspace);
    }

    public Flux<Workspace> getAllWorkspaces() {
        return workspaceRepository.findAll();
    }

    public Mono<Workspace> getWorkspaceById(Long workspaceId) {
        return workspaceRepository.findById(workspaceId)
                .flatMap(workspace -> {
                    Mono<List<Request>> requestsMono = requestRepository
                            .getRequestsByWorkspaceId(workspaceId)
                            .collectList();
                    Mono<List<Workspace>> workspacesMono = workspaceRepository
                            .getWorkspaceByWorkspaceFkId(workspaceId)
                            .collectList();
                    return Mono.zip(requestsMono, workspacesMono).map(tuple -> {
                        workspace.setRequests(tuple.getT1());
                        workspace.setWorkspaces(tuple.getT2());
                        return workspace;
                    });
                });
    }

    public Mono<Void> deleteWorkspaceById(Long id) {
        return workspaceRepository.deleteById(id);
    }
}
