package com.task.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String entity, String field, String value) {
        super(String.format("%s already exists with %s '%s'", entity, field, value));
    }
}
