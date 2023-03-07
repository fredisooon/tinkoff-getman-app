package com.example.getmanapp.service;


import com.example.getmanapp.repository.RequestRepository;
import com.example.getmanapp.repository.WorkspaceRepository;
import com.example.getmanapp.utils.mix.BooleanObject;
import com.example.getmanapp.utils.mix.MassMoveItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
@Transactional
public class MassMoveService {

    private final WorkspaceRepository workspaceRepository;
    private final RequestRepository requestRepository;

    public MassMoveService(WorkspaceRepository workspaceRepository, RequestRepository requestRepository) {
        this.workspaceRepository = workspaceRepository;
        this.requestRepository = requestRepository;
    }


    /**
     * OK
     * @param list
     * @return
     */
    public Mono<BooleanObject> massMove(List<MassMoveItem> list) {
        list.forEach(System.out::println);

        return Flux.fromIterable(list)
                .flatMap(this::byPass)
                .any(result -> !result)
                .map(result -> new BooleanObject(!result))
                .switchIfEmpty(Mono.just(new BooleanObject(true)))
                .onErrorResume(ex -> Mono.just(new BooleanObject(false, ex.getMessage())));
    }
    private Mono<Boolean> byPass(MassMoveItem item) {
        return workspaceRepository
                .findById(item.getWorkspace())
                .flatMap(result -> {
                    log.info(result.getName());
                    Mono<Boolean> findAndSetWorkspaces = findMovedWorkspaces(item.getWorkspaces(), item.getWorkspace());
                    Mono<Boolean> findAndSetRequests = findMovedRequests(item.getRequests(), item.getWorkspace());

                    return Mono.zip(findAndSetWorkspaces, findAndSetRequests, (fW, fR) -> fW && fR);
                })
                .switchIfEmpty(Mono.error(new Exception("Ops...")));
    }
    private Mono<Boolean> findMovedWorkspaces(List<Long> listId, Long newStoreId) {
        if (listId == null || listId.isEmpty())
            return Mono.just(true);

        return Flux.fromIterable(listId)
                .flatMap(workspaceId -> workspaceRepository
                        .findById(workspaceId)
                        .flatMap(foundWorkspace -> {
                            foundWorkspace.setWorkspace_fk_id(newStoreId);
                            return workspaceRepository
                                    .save(foundWorkspace)
                                    .map(savedWorkspace -> true)
                                    .onErrorResume(ex -> Mono.error(new Exception("Can't save workspace. " + ex.getMessage())));
                        })
                        .switchIfEmpty(Mono.just(false))
                        .onErrorResume(ex -> Mono.error(ex)))
                .all(found -> found)
                .map(workspace -> workspace)
                .switchIfEmpty(Mono.just(true))
                .onErrorResume(ex -> Mono.error(ex));
    }
    private Mono<Boolean> findMovedRequests(List<Long> listId, Long newStoreId) {
        if (listId == null || listId.isEmpty())
            return Mono.just(true);

        return Flux.fromIterable(listId)
                .flatMap(requestId -> requestRepository
                        .findById(requestId)
                        .flatMap(foundRequest -> {
                            foundRequest.setWorkspace_id(newStoreId);
                            foundRequest.setHeaders(new HttpHeaders());
                            return requestRepository
                                    .save(foundRequest)
                                    .map(savedRequest -> true)
                                    .onErrorResume(ex -> Mono.error(new Exception("Can't save request. " + ex.getMessage())));
                        })
                        .switchIfEmpty(Mono.just(false))
                        .onErrorResume(ex -> Mono.error(ex)))
                .all(found -> found)
                .map(request -> request)
                .switchIfEmpty(Mono.just(true))
                .onErrorResume(ex -> Mono.error(ex));
    }

}
