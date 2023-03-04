package com.example.getmanapp.repository;

import com.example.getmanapp.model.Workspace;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface WorkspaceRepository extends ReactiveCrudRepository<Workspace, Long> {
    @Query("SELECT * FROM workspace WHERE workspace_fk_id = :workspaceFkId")
    Flux<Workspace> getWorkspaceByWorkspaceFkId(@Param("workspaceFkId") Long workspaceFkId);
    @Query("SELECT id FROM workspace WHERE workspace_fk_id = :workspaceFkId")
    Flux<Long> getListOfWorkspacesIdByWorkspaceFkId(@Param("workspaceFkId") Long workspaceFkId);
}