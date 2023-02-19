package com.example.getmanapp.repository;

import com.example.getmanapp.model.Workspace;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceRepository extends ReactiveCrudRepository<Workspace, Long> {
}
