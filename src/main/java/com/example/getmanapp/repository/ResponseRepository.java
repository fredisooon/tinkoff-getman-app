package com.example.getmanapp.repository;

import com.example.getmanapp.model.Response;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends ReactiveCrudRepository<Response, Long> {
}
