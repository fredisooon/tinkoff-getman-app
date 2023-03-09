package com.example.getmanapp.exceptions.exception;

import com.example.getmanapp.model.Request;
import com.example.getmanapp.model.RequestSnapshot;

public class RequestSnapshotSaveException extends RuntimeException {
    public RequestSnapshotSaveException(RequestSnapshot requestSnapshot) {
        super("Failed to save entity of Request:\n" + requestSnapshot.toString());    }
}
