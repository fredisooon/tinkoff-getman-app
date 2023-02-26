package com.example.getmanapp.repository;

import com.example.getmanapp.model.Request;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RequestRepository extends ReactiveCrudRepository<Request, Long> {
    @Query("SELECT * FROM request WHERE workspace_id = :workspaceId")
    Flux<Request> getRequestsByWorkspace_id(Long workspaceId);
}
