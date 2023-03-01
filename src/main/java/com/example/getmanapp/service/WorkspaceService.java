package com.example.getmanapp.service;

import com.example.getmanapp.model.Request;
import com.example.getmanapp.model.Workspace;
import com.example.getmanapp.repository.RequestRepository;
import com.example.getmanapp.repository.WorkspaceRepository;
import com.example.getmanapp.utils.Id;
import com.example.getmanapp.utils.mix.BooleanObject;
import com.example.getmanapp.utils.mix.MoveObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


/**
 * ВСЁ +/- РАБОТАЕТ НО ВО ВСЕХ МЕТОДАХ НУЖНО ПРОДУМАТЬ ОБРАБОТКУ ИСКЛЮЧЕНИЙ.
 */
@Service
@Slf4j
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final RequestRepository requestRepository;
    private final R2dbcEntityOperations entityOperations;
    @Autowired
    public WorkspaceService(WorkspaceRepository workspaceRepository, RequestRepository requestRepository, R2dbcEntityOperations entityOperations) {
        this.workspaceRepository = workspaceRepository;
        this.requestRepository = requestRepository;
        this.entityOperations = entityOperations;
    }


    /**
    Отрефакторить и делегировать на более мелкие методы.
     **/
    @Transactional(readOnly = true)
    public Mono<Workspace> getWorkspaceById(Long workspaceId) {
        Mono<Workspace> parentWorkspace =
                workspaceRepository.findById(workspaceId);
        Flux<Workspace> childrenWorkspaces =
                workspaceRepository.getWorkspaceByWorkspaceFkId(workspaceId);
        Flux<Request> parentNestedRequests =
                requestRepository.getRequestsByWorkspaceId(workspaceId);

        return parentWorkspace.zipWith(parentNestedRequests.collectList())
                .map(tuple -> {
                    Workspace parent = tuple.getT1();
                    List<Request> nestedRequests = tuple.getT2();
                    parent.setRequests(nestedRequests);
                    return parent;
                }).flatMap(parent -> childrenWorkspaces.collectList().flatMap(childrenWorkspace -> {
                    parent.setWorkspaces(childrenWorkspace);
                    return findChildrenRecursively(childrenWorkspace);
        }).thenReturn(parent)).log();
    }


    private Mono<Void> findChildrenRecursively(List<Workspace> parents) {
        if (parents.isEmpty()) {
            return Mono.empty();
        } else {
            List<Mono<Void>> childrenMonos = new ArrayList<>();
            for (Workspace parent : parents) {
                Flux<Workspace> childrenFlux = entityOperations.select(
                        Query.query(Criteria.where("workspace_fk_id").is(parent.getId())),
                        Workspace.class
                );
                Flux<Request> requestFlux = entityOperations.select(
                        Query.query(Criteria.where("workspace_id").is(parent.getId())),
                        Request.class);

                childrenMonos.add((requestFlux.collectList().flatMap(children -> {
                    parent.setRequests(children);
                    return Mono.empty();
                }).then()));
                childrenMonos.add(childrenFlux.collectList().flatMap(children -> {
                    parent.setWorkspaces(children);
                    return findChildrenRecursively(children);
                }).then());
            }
            return Mono.when(childrenMonos);
        }
    }

    /**
    Добваить логику обновления workspace_fk_id.
    Т.е. чтобы можно было у существующего воркспейса обновить место его пребывания,
    например перенести из root_workspace в my_personal_workspace

     (upd: вроде исправлено)
     **/
    @Transactional
    public Mono<Workspace> updateWorkspaceById(Workspace workspace, Long id) {

        return workspaceRepository.findById(id)
                .flatMap(e -> {
                    e.setName(workspace.getName());
                    e.setDescription(workspace.getDescription());
                    e.setWorkspace_fk_id(workspace.getWorkspace_fk_id());
                    return workspaceRepository.save(e);
                })
                .log();
    }
    /**
    Выходные параметры работают корректно (ID object)
     **/
    @Transactional
    public Mono<Id> saveWorkspace(Workspace workspace) {
        return workspaceRepository.save(workspace)
                .flatMap(e -> Mono.just(new Id(e.getId(), e.getWorkspace_fk_id())))
                .log();
    }

    /**
    Работает
     **/
    @Transactional
    public Mono<BooleanObject> deleteWorkspaceById(Long id) {
        return workspaceRepository
                .findById(id)
                .flatMap(workspace -> workspaceRepository.delete(workspace).thenReturn(new BooleanObject(Boolean.TRUE)))
                .switchIfEmpty(Mono.just(new BooleanObject(Boolean.FALSE)));
    }
    /**
     * Работает, нужно тестить и решить точные форматы возвращаемых ответов.
     */

    @Transactional
    public Mono<BooleanObject> deleteCascadeWorkspace(Long workspaceId) {
        return workspaceRepository.findById(workspaceId)
                .flatMap(workspace -> deleteRequestsByWorkspaceId(workspaceId)
                        .then(deleteChildWorkspacesAndCascade(workspaceId))
                        .then(workspaceRepository.deleteById(workspaceId))
                        .thenReturn(new BooleanObject(Boolean.TRUE)))
                .defaultIfEmpty(new BooleanObject(Boolean.FALSE));
    }
    private Mono<Void> deleteRequestsByWorkspaceId(Long workspaceId) {
        return requestRepository.deleteAllByWorkspaceId(workspaceId);
    }
    private Mono<Void> deleteChildWorkspacesAndCascade(Long workspaceId) {
        return workspaceRepository.getWorkspaceByWorkspaceFkId(workspaceId)
                .flatMap(childWorkspace -> deleteCascadeWorkspace(childWorkspace.getId()))
                .then();
    }

    /**
     * Решить какой формат у RequestBody.
     * Сделать проверкуи наличия воркспейсов с айди указанными во входных параметрах.
     */
    public Mono<BooleanObject> moveWorkspaceToWorkspace(Long id, MoveObject body) {
        return workspaceRepository
                .findById(id)
                .flatMap(workspace -> {
                    workspace.setWorkspace_fk_id(body.getWorkspace());
                    return workspaceRepository.save(workspace).thenReturn(new BooleanObject(Boolean.TRUE));
                });
    }
}