package com.example.getmanapp.repository;

import com.example.getmanapp.model.Request;
import com.example.getmanapp.model.RequestSnapshot;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RequestSnapshotRepository extends ReactiveCrudRepository<RequestSnapshot, Long> {

}
