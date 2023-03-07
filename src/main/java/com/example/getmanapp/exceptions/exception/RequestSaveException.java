package com.example.getmanapp.exceptions.exception;

import com.example.getmanapp.model.Request;

public class RequestSaveException extends RuntimeException{
    public RequestSaveException(Request request) {
        super("Failed to save entity of Request:\n" + request.toString());    }
}
