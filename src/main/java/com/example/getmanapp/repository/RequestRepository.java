package com.example.getmanapp.repository;

import com.example.getmanapp.model.Request;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends ReactiveCrudRepository<Request, Long> {
}
