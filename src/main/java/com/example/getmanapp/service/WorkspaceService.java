package com.example.getmanapp.service;

import com.example.getmanapp.model.Workspace;
import com.example.getmanapp.repository.WorkspaceRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;

    public WorkspaceService(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    public Mono<Workspace> saveWorkspace(Workspace workspace) {
        return workspaceRepository.save(workspace);
    }

    public Flux<Workspace> getAllWorkspaces() {
        return workspaceRepository.findAll();
    }

    public Mono<Workspace> getWorkspaceById(Long id) {
        return workspaceRepository.findById(id);
    }

    public Mono<Void> deleteWorkspaceById(Long id) {
        return workspaceRepository.deleteById(id);
    }
}
