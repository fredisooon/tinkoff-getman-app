package com.example.getmanapp.exceptions.exception;

public class RequestNotFoundException extends RuntimeException{
    public RequestNotFoundException(Long id){
        super("Request not found for ID: " + id);
    }
}
