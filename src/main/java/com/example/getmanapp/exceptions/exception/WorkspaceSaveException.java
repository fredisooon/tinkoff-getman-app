package com.example.getmanapp.exceptions.exception;

import com.example.getmanapp.model.Workspace;

public class WorkspaceSaveException extends RuntimeException{
    public WorkspaceSaveException(Workspace workspace) {
        super("Failed to save entity of Workspace:\n" + workspace.toString());
    }
}
