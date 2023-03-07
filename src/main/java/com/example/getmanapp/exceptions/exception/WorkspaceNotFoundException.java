package com.example.getmanapp.exceptions.exception;

public class WorkspaceNotFoundException extends RuntimeException{
    public WorkspaceNotFoundException(Long workspaceId) {
        super("Workspace not found for ID: " + workspaceId);
    }
}
