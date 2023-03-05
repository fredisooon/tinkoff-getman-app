package com.example.getmanapp.exceptions.exception;

import com.example.getmanapp.model.Workspace;

public class WorkspaceSavingException extends RuntimeException{
    public WorkspaceSavingException(Workspace workspace) {
        super("Failed to save entity of Workspace:\n" + workspace.toString());
    }
}
