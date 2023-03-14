package com.example.getmanapp.service;

import com.example.getmanapp.exceptions.exception.WorkspaceNotFoundException;
import com.example.getmanapp.exceptions.exception.WorkspaceSaveException;
import com.example.getmanapp.model.Workspace;
import com.example.getmanapp.repository.RequestRepository;
import com.example.getmanapp.repository.WorkspaceRepository;
import com.example.getmanapp.utils.ID;
import com.example.getmanapp.utils.mix.AdapterLayer;
import com.example.getmanapp.utils.mix.BooleanObject;
import com.example.getmanapp.utils.mix.MoveObject;
import com.example.getmanapp.utils.mix.ResponseWorkspace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final RequestRepository requestRepository;
    @Autowired
    public WorkspaceService(WorkspaceRepository workspaceRepository, RequestRepository requestRepository) {
        this.workspaceRepository = workspaceRepository;
        this.requestRepository = requestRepository;
    }

    /**
     * OK
     * param Long workspaceId
     * @return Workspace instance
     */
    @Transactional(readOnly = true)
    public Mono<ResponseWorkspace> getWorkspaceById(Long workspaceId) {
        Mono<Workspace> parentWorkspace =
                workspaceRepository.findById(workspaceId)
                        .switchIfEmpty(Mono.error(new WorkspaceNotFoundException(workspaceId)));
        Flux<Long> childrenWorkspaces =
                workspaceRepository.getListOfWorkspacesIdByWorkspaceFkId(workspaceId);
        Flux<Long> parentNestedRequests =
                requestRepository.getRequestsIdByWorkspaceId(workspaceId);

        return parentWorkspace.zipWith(parentNestedRequests.collectList())
                .map(tuple -> {
                    Workspace parent = tuple.getT1();
                    List<Long> nestedRequests = tuple.getT2();
                    parent.setRequests(nestedRequests);
                    return parent;
                }).flatMap(parent -> childrenWorkspaces.collectList().flatMap(cW -> {
                    parent.setWorkspaces(cW);
                    log.info(parent.toString());
                    return Mono.just(AdapterLayer.convertToResponseWorkspace(parent));
                }))
                .log();
    }

    /**
     * OK
     * @param workspaceId
     * @param workspace
     * @return Updated Workspace instance
     */
    @Transactional
    public Mono<Workspace> updateWorkspaceById(Long workspaceId, Workspace workspace) {
        if (workspace == null)
            return Mono.error(new IllegalArgumentException("Workspace must not be null"));

        return workspaceRepository.findById(workspaceId)
                .switchIfEmpty(Mono.error(new WorkspaceNotFoundException(workspaceId)))
                .flatMap(e -> {
                    e.setName(workspace.getName());
                    e.setDescription(workspace.getDescription());
                    return workspaceRepository.save(e).log();
                });
    }

    /**
     * OK
     * param Workspace workspace
     * @return ID instance
     */
    @Transactional
    public Mono<ID> saveWorkspace(Workspace workspace) {
        return workspaceRepository.save(workspace)
                .switchIfEmpty(Mono.error(new WorkspaceSaveException(workspace)))
                .flatMap(e -> Mono.just(new ID(e.getId(), e.getWorkspace_fk_id())))
                .log();
    }

    /**
     * OK
     * param Long workspaceId
     * @return BooleanObject instance
     */
    @Transactional
    public Mono<BooleanObject> deleteWorkspaceById(Long workspaceId) {
        return workspaceRepository.findById(workspaceId)
                .flatMap(workspace -> workspaceRepository.getWorkspaceByWorkspaceFkId(workspaceId)
                        .collectList()
                        .flatMap(workspaces -> {
                            if (workspaces.isEmpty())
                                return workspaceRepository.delete(workspace)
                                        .thenReturn(new BooleanObject(Boolean.TRUE));
                            else
                                return Mono.error(new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,
                                                   "Method Not Allowed"));
                        }))
                .switchIfEmpty(Mono.just(new BooleanObject(Boolean.FALSE,
                                            "Workspace not found for ID: " + workspaceId)));
    }
    /**
     * OK
     * param Long workspaceId
     * @return BooleanOBject instance
     */
    @Transactional
    public Mono<BooleanObject> deleteCascadeWorkspace(Long workspaceId) {
        return workspaceRepository.findById(workspaceId)
                .flatMap(workspace -> deleteNestedRequests(workspaceId)
                        .then(deleteChildWorkspaces(workspaceId))
                        .then(workspaceRepository.deleteById(workspaceId))
                        .thenReturn(new BooleanObject(Boolean.TRUE)))


                .switchIfEmpty(Mono.just(new BooleanObject(Boolean.FALSE,
                                            "Workspace not found for ID: " + workspaceId))).log();
    }
    private Mono<Void> deleteNestedRequests(Long workspaceId) {
        return requestRepository.deleteAllByWorkspaceId(workspaceId).log();
    }
    private Mono<Void> deleteChildWorkspaces(Long workspaceId) {
        return workspaceRepository.getWorkspaceByWorkspaceFkId(workspaceId)
                .flatMap(childWorkspace -> deleteCascadeWorkspace(childWorkspace.getId()))
                .then();
    }

    /**
     * OK
     * @param id
     * @param body
     * @return BooleanObject instance
     */
    public Mono<BooleanObject> moveWorkspaceToWorkspace(Long id, MoveObject body) {
        if (body == null)
            return Mono.error(new IllegalArgumentException("Body must not be null"));

        return workspaceRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new WorkspaceNotFoundException(id)))
                .flatMap(workspace -> workspaceRepository.findById(body.getWorkspace())
                                .switchIfEmpty(Mono.error(new WorkspaceNotFoundException(body.getWorkspace())))
                                .flatMap(storeWorkspace -> {
                                        workspace.setWorkspace_fk_id(body.getWorkspace());
                                        return workspaceRepository.save(workspace).thenReturn(new BooleanObject(Boolean.TRUE));
                                }));
    }
}