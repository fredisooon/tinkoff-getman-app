package com.example.getmanapp.repository;

import com.example.getmanapp.model.Workspace;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface WorkspaceRepository extends ReactiveCrudRepository<Workspace, Long> {
    @Modifying
    @Query("INSERT INTO workspace (name, description, workspace_fk_id) VALUES ($1, $2, $3)")
    Mono<Workspace> save(String name, String description, Long fkId);
}
