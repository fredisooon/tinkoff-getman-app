package com.example.getmanapp.exceptions;

import com.example.getmanapp.exceptions.exception.WorkspaceNotFoundException;
import com.example.getmanapp.exceptions.exception.WorkspaceSavingException;
import com.example.getmanapp.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(WorkspaceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleWorkspaceNotFoundException(WorkspaceNotFoundException ex) {
        return new ErrorResponse("workspace_not_found", ex.getMessage());
    }

    @ExceptionHandler(WorkspaceSavingException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ErrorResponse handleWorkspaceSavingException(WorkspaceSavingException ex) {
        return new ErrorResponse("workspace_saving_failed", ex.getMessage());
    }

}
