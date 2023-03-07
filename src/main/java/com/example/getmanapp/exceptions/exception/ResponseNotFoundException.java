package com.example.getmanapp.exceptions.exception;

public class ResponseNotFoundException extends RuntimeException{
    public ResponseNotFoundException(Long id) {
        super("Response not found for ID: " + id);
    }
}
