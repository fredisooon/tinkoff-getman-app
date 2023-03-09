package com.example.getmanapp.exceptions;

import com.example.getmanapp.exceptions.exception.*;
import com.example.getmanapp.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(WorkspaceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleWorkspaceNotFoundException(WorkspaceNotFoundException ex) {
        return new ErrorResponse("workspace_not_found", ex.getMessage());
    }

    @ExceptionHandler(WorkspaceSaveException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ErrorResponse handleWorkspaceSaveException(WorkspaceSaveException ex) {
        return new ErrorResponse("workspace_save_failed", ex.getMessage());
    }

    @ExceptionHandler(RequestNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleRequestNotFoundException(RequestNotFoundException ex) {
        return new ErrorResponse("request_not_found", ex.getMessage());
    }

    @ExceptionHandler(RequestSaveException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ErrorResponse handleRequestSaveException(RequestSaveException ex) {
        return new ErrorResponse("request_save_failed", ex.getMessage());
    }

    @ExceptionHandler(ResponseSaveException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ErrorResponse handleResponseSaveException(ResponseSaveException ex) {
        return new ErrorResponse("response_save_failed", ex.getMessage());
    }

    @ExceptionHandler(ResponseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleResponseNotFoundException(ResponseNotFoundException ex) {
        return new ErrorResponse("response_not_found", ex.getMessage());
    }

    @ExceptionHandler(RequestSnapshotSaveException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ResponseBody
    public ErrorResponse handleRequestSnapshotSaveException(RequestSnapshotSaveException ex) {
        return new ErrorResponse("requestSnapshot_save_failed", ex.getMessage());
    }


    @ExceptionHandler(RequestSnapshotNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleRequestSnapshotNotFoundException(RequestSnapshotNotFoundException ex) {
        return new ErrorResponse("requestSnapshot_not_found", ex.getMessage());
    }
}
