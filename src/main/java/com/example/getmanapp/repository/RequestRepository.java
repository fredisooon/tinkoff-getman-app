package com.example.getmanapp.repository;

import com.example.getmanapp.model.Request;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface RequestRepository extends ReactiveCrudRepository<Request, Long> {
    @Query("SELECT id FROM request WHERE workspace_id = :workspaceId")
    Flux<Long> getRequestsIdByWorkspaceId(Long workspaceId);

    @Query("DELETE FROM request WHERE workspace_id = :id")
    Mono<Void> deleteAllByWorkspaceId(Long id);
}
