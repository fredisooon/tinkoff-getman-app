package com.example.getmanapp.repository;

import com.example.getmanapp.model.Workspace;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface WorkspaceRepository extends ReactiveCrudRepository<Workspace, Long> {
    @Query("SELECT * FROM workspace WHERE workspace_fk_id = :worksapceFkId")
    Flux<Workspace> getWorkspaceByWorkspaceFkId(Long workspaceFkId);
}
