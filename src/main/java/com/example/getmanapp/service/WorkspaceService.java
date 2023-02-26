package com.example.getmanapp.service;

import com.example.getmanapp.model.Request;
import com.example.getmanapp.model.Workspace;
import com.example.getmanapp.repository.WorkspaceRepository;

import com.example.getmanapp.utils.Id;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.repository.core.support.RepositoryMethodInvocationListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;


@Service
@Slf4j
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;

    @Autowired
    public WorkspaceService(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    public Mono<Id> saveWorkspace(Workspace workspace, String fkId) {
        log.info("Second step: " + workspace.toString() + " " + fkId);
        Mono<Workspace> newWorkspace
                = workspaceRepository.save(workspace.getName(), workspace.getDescription(), Long.parseLong(fkId));

        log.info("third step: " + newWorkspace);

        return newWorkspace.flatMap(e -> {
            log.info("Fourth step: ");
            Id id = new Id();
            id.setId(e.getId());
            id.setParent(e.getWorkspace_fk_id());

            log.info(id.toString());
            return Mono.just(id);
        });

//        return Mono.just(new Id()).flatMap(e -> newWorkspace.map(nw -> {
//            log.info("Fourth step: ");
//            e.setId(nw.getId());
//            e.setParent(nw.getWorkspace_fk_id());
//
//            log.info(e.toString());
//            return e;
//        }));
    }

    public Flux<Workspace> getAllWorkspaces() {
        return workspaceRepository.findAll();
    }

    public Mono<Workspace> getWorkspaceById(Long id) {
        return workspaceRepository.findById(id);
    }

    public Mono<Workspace> updateWorkspaceById(Workspace workspace, Long id) {
        Mono<Workspace> workspaceToUpdate = workspaceRepository.findById(id);

        return workspaceToUpdate.map(e -> {
            e.setName(workspace.getName());
            e.setDescription(workspace.getDescription());
            e.setWorkspaces(getChildrenWorkspaces(workspace));
            return e;
        });
    }

    public Mono<Void> deleteWorkspaceById(Long id) {
        return workspaceRepository.deleteById(id);
    }

    private List<Workspace> getChildrenWorkspaces(Workspace workspace) {
        ConnectionFactory connectionFactory
                = ConnectionFactories.get("r2dbc:postgresql://localhost:5432/postgres");

        R2dbcEntityTemplate r2dbcEntityTemplate = new R2dbcEntityTemplate(connectionFactory);

        Flux<Workspace> workspaces = r2dbcEntityTemplate.select(Workspace.class)
                .from("workspace")
                .matching(query(where("workspace_fk_id").is(workspace.getId()))).all();

        List<Workspace> toList = workspaces.collectList().block();

        return toList;
    }

    private List<Request> getRequests(Workspace workspace) {
        ConnectionFactory connectionFactory
                = ConnectionFactories.get("r2dbc:postgresql://localhost:5432/postgres");

        R2dbcEntityTemplate r2dbcEntityTemplate = new R2dbcEntityTemplate(connectionFactory);

        Flux<Request> requests = r2dbcEntityTemplate.select(Request.class)
                .from("request")
                .matching(query(where("workspace_id").is(workspace.getId()))).all();

        List<Request> toList = requests.collectList().block();

        return toList;
    }
}
