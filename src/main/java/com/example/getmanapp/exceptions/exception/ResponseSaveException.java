package com.example.getmanapp.exceptions.exception;

import com.example.getmanapp.model.Response;

public class ResponseSaveException extends RuntimeException{
    public ResponseSaveException(Response response) {
        super("Failed to save entity of Response:\n" + response.toString());
    }
}
