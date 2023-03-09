package com.example.getmanapp.exceptions.exception;

public class RequestSnapshotNotFoundException extends RuntimeException {
    public RequestSnapshotNotFoundException(Long id){
        super("Request Snapshot not found for ID: " + id);
    }
}
